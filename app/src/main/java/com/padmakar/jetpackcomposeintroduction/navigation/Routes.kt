package com.padmakar.jetpackcomposeintroduction.navigation

// navigation/Routes.kt
sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object ForgotPassword : Routes("forgotPassword")
    object RegisterOTP : Routes("registerOTP/{email}/{phone}") {
        fun createRegisterRoute(email: String, phone: String) = "registerOTP/$email/$phone"
    }
    object LoginOTP : Routes("loginOTP/{otpParam}/{inputType}") {
        fun createLoginOTPRoute(sendOtpParam: String, inputType: String) = "loginOTP/$sendOtpParam/$inputType"
    }

    object ForgotPasswordOTP : Routes("forgotPasswordOTP/{otpParam}/{inputType}") {
        fun createForgotPasswordOTPRoute(sendOtpParam: String, inputType: String) = "forgotPasswordOTP/$sendOtpParam/$inputType"
    }
}
