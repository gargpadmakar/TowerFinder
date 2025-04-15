package com.padmakar.jetpackcomposeintroduction.screen.register.model

data class RegisterOTPUiState(
    var mobileOtp: String = "",
    var emailOtp: String = "",
    var timeLeft: Int = 120,
    var isTimerRunning: Boolean = true)
