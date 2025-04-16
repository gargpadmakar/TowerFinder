package com.padmakar.jetpackcomposeintroduction.screen.login.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padmakar.jetpackcomposeintroduction.core.BaseViewModel
import com.padmakar.jetpackcomposeintroduction.screen.login.Validators
import com.padmakar.jetpackcomposeintroduction.screen.login.model.LoginUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailOrPhoneChange(input: String) {
        val inputType = when {
            Validators.isValidEmail(input) -> "Email"
            Validators.isValidPhone(input) -> "Mobile"
            else -> "Invalid"
        }

        _uiState.value = _uiState.value.copy(
            emailOrPhone = input,
            inputType = inputType
        )
        validateForm()
    }

    fun onPasswordChange(input: String) {
        _uiState.value = _uiState.value.copy(password = input)
        validateForm()
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(passwordVisible = !_uiState.value.passwordVisible)
    }

    fun validateForm(): Boolean {
        val emailOrPhoneError = when {
            uiState.value.emailOrPhone.isEmpty() -> "Enter email or phone"
            uiState.value.inputType == "Invalid" -> "Invalid email or phone"
            else -> null
        }

        val passwordError = when {
            uiState.value.password.isEmpty() -> "Password cannot be empty"
            uiState.value.password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }

        _uiState.value = uiState.value.copy(
            emailOrPhoneError = emailOrPhoneError,
            passwordError = passwordError
        )

        return emailOrPhoneError == null && passwordError == null
    }

    fun performLogin(onLoginComplete: () -> Unit) {
        viewModelScope.launch {
            showLoading(true) // Show loading indicator (pass true)
            delay(2000) // Simulating API or DB operation
            showLoading(false) // Hide loading indicator (pass false)
            onLoginComplete() // Call the callback after login is complete
        }
    }




    fun clearInputs() {
        _uiState.value = LoginUiState() // Reset to default state
    }

}
