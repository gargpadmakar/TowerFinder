package com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.model

data class ForgotPasswordOTPUiState(
    val otp: String = "",
    val timer: Int = 120,
    val isTimerRunning: Boolean = true
)
