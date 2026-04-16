package com.infophone.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infophone.domain.model.User
import com.infophone.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel - Consumes the Domain layer's Use Case [3]
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase // Injected Use Case
) : ViewModel() {

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    fun loadUser(userId: String) {
        viewModelScope.launch {
            try {
                // Execute Use Case (business logic)
                val user = getUserUseCase(userId)

                // Update UI State with Domain Entity
                _userState.value = user

            } catch (e: Exception) {
                // Handle standardized Domain exceptions and map to UI errors
                // e.g., showToast("Error loading user")
            }
        }
    }
}