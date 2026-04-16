package com.infophone.auth.domain.model

sealed class AuthPage {
    data object LoginPage : AuthPage()
    data object OtpPage : AuthPage()
}