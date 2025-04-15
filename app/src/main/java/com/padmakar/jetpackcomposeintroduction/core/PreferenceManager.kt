package com.padmakar.jetpackcomposeintroduction.core

import android.content.Context
import android.content.SharedPreferences
import com.padmakar.jetpackcomposeintroduction.utils.AppConfig

class PreferenceManager(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(AppConfig.SHARED_PREF, Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String = ""): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    fun clearAndKeepUserPhone(): Boolean {
        val userPhone = getString(AppConfig.USER_PHONE, "")
        prefs.edit().clear().apply()
        if (userPhone.isNotEmpty()) {
            putString(AppConfig.USER_PHONE, userPhone)
        }
        return true
    }
}