package com.padmakar.jetpackcomposeintroduction.navigation

import androidx.navigation.NavController


// ✅ Navigates from Register screen to RegisterOTP screen with email & phone
fun navigateToRegisterOTP(navController: NavController, email: String, phone: String) {
    val safeEmail = email.trim()
    val safePhone = phone.trim()
    navController.navigate(Routes.RegisterOTP.createRegisterRoute(safeEmail, safePhone))
}

// ✅ Navigates to Login screen
fun navigateToLogin(navController: NavController) {
    navController.navigate(Routes.Login.route)
}

// ✅ Navigates from Login screen to LoginOTP screen with parameters
fun navigateToLoginOTP(navController: NavController, sendOtpParam: String, inputType: String) {
    val safeSendOtpParam = sendOtpParam.trim()
    val safeInputType = inputType.trim()
    navController.navigate(Routes.LoginOTP.createLoginOTPRoute(safeSendOtpParam, safeInputType))
}

// ✅ Navigates to Register screen
fun navigateToRegister(navController: NavController) {
    navController.navigate(Routes.Register.route)
}

// ✅ Navigates to Forgot Password screen
fun navigateToForgotPassword(navController: NavController) {
    navController.navigate(Routes.ForgotPassword.route)
}

// ✅ Navigates from Forgot Password screen to OTP screen with parameters
fun navigateToForgotPasswordOTP(navController: NavController, sendOtpParam: String, inputType: String) {
    val safeSendOtpParam = sendOtpParam.trim()
    val safeInputType = inputType.trim()
    navController.navigate(Routes.ForgotPasswordOTP.createForgotPasswordOTPRoute(safeSendOtpParam, safeInputType))
}





