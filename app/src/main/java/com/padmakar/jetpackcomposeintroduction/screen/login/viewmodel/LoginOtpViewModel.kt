package com.padmakar.jetpackcomposeintroduction.screen.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padmakar.jetpackcomposeintroduction.screen.login.model.LoginOtpUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginOtpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginOtpUiState())
    val uiState: StateFlow<LoginOtpUiState> = _uiState

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_uiState.value.timer > 0) {
                delay(1000)
                _uiState.value = _uiState.value.copy(timer = _uiState.value.timer - 1)
            }
            _uiState.value = _uiState.value.copy(isTimerRunning = false)
        }
    }

    fun updateOtp(input: String) {
        _uiState.value = _uiState.value.copy(
            otp = input,
            otpError = null // Clear error when user starts typing
        )
    }


    fun resendOtp() {
        _uiState.value = LoginOtpUiState()
        startTimer()
    }
    fun validateOtp(): Boolean {
        val otp = _uiState.value.otp.trim()

        val error = when {
            otp.isEmpty() -> "OTP cannot be empty"
            otp.length != 4 -> "OTP must be 4 digits"
            !otp.all { it.isDigit() } -> "OTP must contain only digits"
            else -> null
        }

        _uiState.value = _uiState.value.copy(otpError = error)

        return error == null
    }

    fun clearOtp() {
        _uiState.value = _uiState.value.copy(otp = "")
    }
}
