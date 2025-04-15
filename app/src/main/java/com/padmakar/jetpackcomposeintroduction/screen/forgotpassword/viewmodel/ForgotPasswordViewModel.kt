package com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.viewmodel

import androidx.lifecycle.ViewModel
import com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.model.ForgotPasswordUiState
import com.padmakar.jetpackcomposeintroduction.screen.login.Validators
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForgotPasswordViewModel:ViewModel() {
    private val _uiState =MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState


    fun onEmailOrMobileChange(input: String) {
        val inputType = when {
            Validators.isValidEmail(input) -> "Email"
            Validators.isValidPhone(input) -> "Mobile"
            else -> "Invalid"
        }

        val errorMessage = when (inputType) {
            "Invalid" -> "Please enter a valid email or mobile number"
            else -> null
        }

        _uiState.value = _uiState.value.copy(
            emailOrMobile = input,
            inputType = inputType,
            emailError = errorMessage
        )
    }

    fun onNewPasswordChange(value: String) {
        val passwordError = if (value.length >= 8) null else _uiState.value.passwordError

        _uiState.value = _uiState.value.copy(
            newPassword = value,
            passwordError = passwordError,
            confirmPasswordError = if (_uiState.value.verifyPassword == value) null else _uiState.value.confirmPasswordError
        )
    }


    fun onVerifyPasswordChange(value: String) {
        val confirmPasswordError = if (value == _uiState.value.newPassword && value.length >= 8) null else _uiState.value.confirmPasswordError

        _uiState.value = _uiState.value.copy(
            verifyPassword = value,
            confirmPasswordError = confirmPasswordError
        )
    }


    fun togglePasswordVisibility(){
        _uiState.value=_uiState.value.copy(passwordNewVisible = !_uiState.value.passwordNewVisible)
    }

    fun toggleVerifyPasswordVisibility() {
        _uiState.value = _uiState.value.copy(passwordVerifyVisible = !_uiState.value.passwordVerifyVisible)
    }

    fun validateForm(): Boolean {
        val state = _uiState.value

        val emailOrMobileValid = state.emailOrMobile.isNotBlank()
        val passwordValid = state.newPassword.length >= 8
        val confirmPasswordNotEmpty = state.verifyPassword.isNotBlank()
        val passwordsMatch = state.newPassword == state.verifyPassword

        val emailError = if (!emailOrMobileValid) "Please enter email or mobile number" else null
        val passwordError = if (!passwordValid) "Password must be at least 8 characters" else null

        val confirmPasswordError = when {
            !confirmPasswordNotEmpty -> "Please confirm your password"
            !passwordsMatch -> "Passwords do not match"
            else -> null
        }

        _uiState.value = state.copy(
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        )

        return emailError == null && passwordError == null && confirmPasswordError == null
    }

}