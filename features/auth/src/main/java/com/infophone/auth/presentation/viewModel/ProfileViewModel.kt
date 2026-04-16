package com.infophone.auth.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infophone.auth.domain.model.User
import com.infophone.auth.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {




    fun saveProfile(
        name: String? = null,
        about: String? = null,
        profileImg: String? = null,
        email: String? = null
    ) {
        viewModelScope.launch {
            authUseCases.saveUser(
                User(
                    userName = name,
                    userAbout = about,
                    userProfileImg = profileImg,
                    userEmail = email
                )
            )
        }
    }
}