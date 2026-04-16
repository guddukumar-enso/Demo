package com.infophone.media.presentation.camera

import android.Manifest
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    modifier: Modifier,
    viewModel: CameraPreviewViewModel = hiltViewModel(),
    onClose: () -> Unit,
    onFinalSend: (List<Uri>, String) -> Unit
) {
    var showCamera by remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val capturedImages by viewModel.capturedImages.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (cameraPermissionState.status.isGranted) {
            showCamera = true
        } else {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (viewModel.isPreviewMode && capturedImages.isNotEmpty()) {
        PhotoPreviewScreen(
            capturedUris = capturedImages,
            onBack = { viewModel.isPreviewMode = false }, // Returns to Camera
            onDelete = { uri -> viewModel.removeImage(uri) },
            onSend = { uris, message -> onFinalSend(uris, message)}
        )
    } else {
        CameraPreviewScreen(
            viewModel = viewModel,
            onClose = onClose,
            onOpenPreview = { viewModel.isPreviewMode = true }
        )
    }

    // Camera Overlay
    if (showCamera && cameraPermissionState.status.isGranted) {
        // Handle system back button to close camera
        BackHandler {
            // If in preview mode, go back to camera; otherwise close camera
            if (viewModel.isPreviewMode) {
                viewModel.isPreviewMode = false
            } else {
                showCamera = false
            }
        }
    }
}