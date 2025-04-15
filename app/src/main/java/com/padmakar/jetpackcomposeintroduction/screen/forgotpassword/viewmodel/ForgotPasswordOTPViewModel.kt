package com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.model.ForgotPasswordOTPUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForgotPasswordOTPViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordOTPUiState())
    var uiState: StateFlow<ForgotPasswordOTPUiState> = _uiState

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
        _uiState.value = _uiState.value.copy(otp = input)
    }

    fun resendOtp() {
        _uiState.value = ForgotPasswordOTPUiState()
        startTimer()
    }

    fun clearOtp() {
        _uiState.value = _uiState.value.copy(otp = "")
    }
}