package com.padmakar.jetpackcomposeintroduction.navigation

// navigation/Routes.kt
// navigation/Routes.kt

sealed class Routes(val route: String) {
    /**
     *  Auth Nav Graph
     *
     *  This section handles all the routes related to authentication.
     */

    /**
     * Login screen route.
     */
    object Login : Routes("login")

    /**
     * Register screen route.
     */
    object Register : Routes("register")

    /**
     * Forgot Password screen route.
     */
    object ForgotPassword : Routes("forgotPassword")

    /**
     * Register OTP screen route.
     *
     * @param email The email address used for registration.
     * @param phone The phone number associated with the registration.
     */
    object RegisterOTP : Routes("registerOTP/{email}/{phone}") {
        fun createRegisterRoute(email: String, phone: String) = "registerOTP/$email/$phone"
    }

    /**
     * Login OTP screen route.
     *
     * @param otpParam The OTP parameter that is sent for login.
     * @param inputType Type of input, either "email" or "phone".
     */
    object LoginOTP : Routes("loginOTP/{otpParam}/{inputType}") {
        fun createLoginOTPRoute(sendOtpParam: String, inputType: String) = "loginOTP/$sendOtpParam/$inputType"
    }

    /**
     * Forgot Password OTP screen route.
     *
     * @param otpParam The OTP parameter used for resetting the password.
     * @param inputType Type of input, either "email" or "phone".
     */
    object ForgotPasswordOTP : Routes("forgotPasswordOTP/{otpParam}/{inputType}") {
        fun createForgotPasswordOTPRoute(sendOtpParam: String, inputType: String) = "forgotPasswordOTP/$sendOtpParam/$inputType"
    }

    /**
     *  Profile Nav Graph
     *
     *  This section handles all the routes related to the user profile.
     */

    /**
     * Profile screen route.
     */
    object Profile : Routes("profile")
}

