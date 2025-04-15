package com.padmakar.jetpackcomposeintroduction.utils

import android.util.Log

class Logger(private val tag: String) {

    // Log Level Constants
    companion object {
        const val DEBUG = "DEBUG"
        const val INFO = "INFO"
        const val ERROR = "ERROR"
        const val WARN = "WARN"
    }

    // Log a message with DEBUG level
    fun debug(message: String) {
        Log.d(tag, message)
    }

    // Log a message with INFO level
    fun info(message: String) {
        Log.i(tag, message)
    }

    // Log a message with ERROR level
    fun error(message: String) {
        Log.e(tag, message)
    }

    // Log a message with WARN level
    fun warn(message: String) {
        Log.w(tag, message)
    }

    // Log a message with custom log level
    fun log(level: String, message: String) {
        when (level) {
            DEBUG -> debug(message)
            INFO -> info(message)
            ERROR -> error(message)
            WARN -> warn(message)
            else -> Log.v(tag, message) // Default to VERBOSE if unknown level
        }
    }
}
