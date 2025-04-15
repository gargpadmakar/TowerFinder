package com.padmakar.jetpackcomposeintroduction.screen.login


import android.util.Patterns

object Validators {
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.matches(Regex("^[6-9]\\d{9}$"))
    }
}

