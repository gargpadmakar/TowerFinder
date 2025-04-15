package com.padmakar.jetpackcomposeintroduction.screen.forgotpassword.model

data class ForgotPasswordUiState(var emailOrMobile: String = "",
                                 val inputType: String = "Invalid",
                                 var newPassword: String = "",
                                 var verifyPassword: String = "",
                                 var passwordNewVisible: Boolean = false,
                                 var passwordVerifyVisible: Boolean = false,
                                 val emailError: String? = null,
                                 val passwordError: String? = null,
                                 val confirmPasswordError: String? = null
)
