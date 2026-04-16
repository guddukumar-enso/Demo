package com.infophone.media.presentation.camera

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.camera.core.*
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class CameraPreviewViewModel @Inject constructor() : ViewModel() {
    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    // List of all photos captured in the current session
    private val _capturedImages = MutableStateFlow<List<Uri>>(emptyList())
    val capturedImages: StateFlow<List<Uri>> = _capturedImages

    // Flag to switch between Camera and Preview screen
    var isPreviewMode by mutableStateOf(false)

    // 1. Define the ImageCapture use case
    private val imageCaptureUseCase = ImageCapture.Builder()
        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
        .build()

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequest.update { newSurfaceRequest }
        }
    }

    private val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    suspend fun bindToCamera(appContext: Context, lifecycleOwner: LifecycleOwner) {
        val processCameraProvider = ProcessCameraProvider.awaitInstance(appContext)
        try {
            processCameraProvider.unbindAll()
            processCameraProvider.bindToLifecycle(
                lifecycleOwner,
                DEFAULT_BACK_CAMERA, // Switched to Back Camera for better testing
                cameraPreviewUseCase,
                imageCaptureUseCase // 2. Bind ImageCapture here
            )
            awaitCancellation()
        } finally {
            processCameraProvider.unbindAll()
        }
    }

    fun capturePhoto(context: Context) {
        // Create unique name for the file
        val name = "IMG_${System.currentTimeMillis()}"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

        // Setup image capture listener, which is triggered after photo has been taken
        imageCaptureUseCase.takePicture(
            outputOptions,
            cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    println("Photo capture failed: ${exc.message}")
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    output.savedUri?.let { uri ->
                        // CRITICAL: Switch to Main thread to update UI/ViewModel
                        Handler(Looper.getMainLooper()).post {
                            _capturedImages.update { it + uri }
//                            onPhotoCaptured(uri)
                        }
                    }
                }
            }
        )
    }

    fun removeImage(uri: Uri) {
        _capturedImages.update { it.filterNot { item -> item == uri } }
        if (_capturedImages.value.isEmpty()) isPreviewMode = false
    }

    fun sendMultipleImagesMessage(uris: List<Uri>, message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            /*try {
                // 1. Map URIs to a list of File or byte arrays if your API requires it
                // 2. Perform the repository call
                val result = chatRepository.sendImages(
                    imageUris = uris,
                    caption = message
                )

                // 3. Handle success/failure UI state
                withContext(Dispatchers.Main) {
                    if (result.isSuccess) {
                        println("Message sent successfully with ${uris.size} images")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Show error toast or snackbar
                    println("Failed to send images: ${e.message}")
                }
            }*/
        }
    }

    override fun onCleared() {
        super.onCleared()
        cameraExecutor.shutdown()
    }
}