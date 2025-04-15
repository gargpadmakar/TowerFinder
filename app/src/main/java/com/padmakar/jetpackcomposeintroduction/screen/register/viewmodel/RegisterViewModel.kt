package com.padmakar.jetpackcomposeintroduction.screen.register.viewmodel

import androidx.lifecycle.ViewModel
import com.padmakar.jetpackcomposeintroduction.screen.register.model.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onFullNameChange(input: String) {
        _uiState.value = _uiState.value.copy(fullName = input, fullNameError = null)
    }

    fun onEmailChange(input: String) {
        _uiState.value = _uiState.value.copy(email = input, emailError = null)
    }

    fun onMobileNumberChange(input: String) {
        _uiState.value = _uiState.value.copy(mobileNumber = input, mobileNumberError = null)
    }

    fun onPasswordChange(input: String) {
        _uiState.value = _uiState.value.copy(password = input, passwordError = null)
    }

    fun onOrganizationChange(input: String) {
        _uiState.value = _uiState.value.copy(organization = input, organizationError = null)
    }

    fun onDesignationChange(input: String) {
        _uiState.value = _uiState.value.copy(designation = input, designationError = null)
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(passwordVisible = !_uiState.value.passwordVisible)
    }

    // 👉 Call this on submit
    fun validateForm(): Boolean {
        val fullNameError = if (_uiState.value.fullName.isEmpty()) "Full Name is required" else null
        val emailError = if (_uiState.value.email.isEmpty()) "Email is required" else null
        val mobileNumberError = if (_uiState.value.mobileNumber.length != 10) "Enter a valid 10-digit mobile number" else null
        val passwordError = if (_uiState.value.password.length < 6) "Password should be at least 6 characters" else null
        val organizationError = if (_uiState.value.organization.isEmpty()) "Organization is required" else null
        val designationError = if (_uiState.value.designation.isEmpty()) "Designation is required" else null

        _uiState.value = _uiState.value.copy(
            fullNameError = fullNameError,
            emailError = emailError,
            mobileNumberError = mobileNumberError,
            passwordError = passwordError,
            organizationError = organizationError,
            designationError = designationError,
        )

        return listOf(
            fullNameError,
            emailError,
            mobileNumberError,
            passwordError,
            organizationError,
            designationError
        ).all { it == null }
    }

    fun clearInputs() {
        _uiState.value = RegisterUiState()
    }
}

