package com.padmakar.jetpackcomposeintroduction.screen.login.model


data class LoginUiState(
    val emailOrPhone: String = "",
    val password: String = "",
    val inputType: String = "Invalid",
    val passwordVisible: Boolean = false,
    val emailOrPhoneError: String? = null,
    val passwordError: String? = null
)

