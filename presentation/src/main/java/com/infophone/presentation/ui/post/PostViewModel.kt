package com.infophone.presentation.ui.post

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infophone.domain.model.Post
import com.infophone.domain.usecase.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
): ViewModel() {
    private val _posts = mutableStateOf<List<Post>>(emptyList())
    val posts: MutableState<List<Post>> = _posts

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                // Execute Use Case (business logic)
                val post = getPostUseCase()

                // Update UI State with Domain Entity
                _posts.value = post

            } catch (e: Exception) {
                Log.d("PostViewModel", "Error: ${e.message}")
                // Handle standardized Domain exceptions and map to UI errors
                // e.g., showToast("Error loading user")
            }
        }
    }
}