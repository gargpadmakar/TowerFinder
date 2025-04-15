package com.padmakar.jetpackcomposeintroduction.screen.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padmakar.jetpackcomposeintroduction.screen.register.model.RegisterOTPUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterOTPViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterOTPUiState())
    val uiState: MutableStateFlow<RegisterOTPUiState> = _uiState

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_uiState.value.timeLeft > 0) {
                delay(1000)
                _uiState.value = _uiState.value.copy(timeLeft = _uiState.value.timeLeft - 1)
            }
            _uiState.value = _uiState.value.copy(isTimerRunning = false)
        }
    }

    fun resetTimer() {
        _uiState.value = _uiState.value.copy(timeLeft = 120, isTimerRunning = true)
        startTimer()
    }

    fun stopTimer() {
        _uiState.value = _uiState.value.copy(isTimerRunning = false)
        // Timer coroutine will automatically stop when timeLeft hits 0
    }

    fun onMobileOtpChange(input: String) {
        _uiState.value = _uiState.value.copy(mobileOtp = input)
    }

    fun onEmailOtpChange(input: String) {
        _uiState.value = _uiState.value.copy(emailOtp = input)
    }
}
