package com.padmakar.jetpackcomposeintroduction.screen.register.model

data class RegisterUiState(
    val fullName: String = "",
    val email: String = "",
    val mobileNumber: String = "",
    val password: String = "",
    val organization: String = "",
    val designation: String = "",
    val passwordVisible: Boolean = false,
    val fullNameError: String? = null,  // Error message for full name
    val emailError: String? = null,     // Error message for email
    val mobileNumberError: String? = null, // Error message for mobile number
    val passwordError: String? = null,   // Error message for password
    val organizationError: String? = null, // Error message for organization
    val designationError: String? = null, // Error message for designation
)

