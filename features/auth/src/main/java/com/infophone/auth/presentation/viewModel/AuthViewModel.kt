package com.infophone.auth.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infophone.auth.domain.model.AuthPage
import com.infophone.auth.domain.model.User
import com.infophone.auth.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {



    val loginItemsList = listOf(
        AuthPage.LoginPage,
        AuthPage.OtpPage
    )


    var phone by mutableStateOf("")
        private set
    var phoneError by mutableStateOf<String?>(null)
        private set

    var otp by mutableStateOf("")
        private set
    var otpError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set




    fun savePhoneInfo(
        countryCode: String,
        countryIso: String,
        phoneNumber: String,
        dialNumber: String
    ) {
        viewModelScope.launch {
            authUseCases.saveUser(
                User(
                    countryCode = countryCode,
                    countryIso = countryIso,
                    phoneNumber = phoneNumber,
                    dialNumber = dialNumber,
                    createdAt = System.currentTimeMillis()
                )
            )
        }
    }





    fun onPhoneChanged(newPhone: String) {
        phone = newPhone.filter { it.isDigit() }
        phoneError = validatePhoneNumber(phone)
    }

    fun onOtpChanged(newOtp: String) {
        otp = newOtp
        otpError = validateOtp(otp)
    }

    private fun validatePhoneNumber(phone: String): String? {
        return when {
            phone.isBlank() -> "Phone number cannot be empty"
            phone.length < 7 || phone.length > 15 -> "Invalid phone number"
            else -> null
        }
    }

    private fun validateOtp(otp: String): String? {
        val validOtp = "123456"
        return when {
            otp.length != 6 -> "OTP must be 6 digits"
            otp != validOtp -> "Invalid otp"
            else -> null
        }
    }
}