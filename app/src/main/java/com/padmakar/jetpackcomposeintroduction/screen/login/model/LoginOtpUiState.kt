package com.padmakar.jetpackcomposeintroduction.screen.login.model

data class LoginOtpUiState(
    val otp: String = "",
    val timer: Int = 120,
    val isTimerRunning: Boolean = true,
    val otpError: String? = null
)
