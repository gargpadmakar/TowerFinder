package com.padmakar.jetpackcomposeintroduction.core

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

open class BaseEventHandler(application: Application) : AndroidViewModel(application) {

    val isLoading = mutableStateOf(false)

    fun showLoading() {
        Log.d("Loader", "Loading started")
        isLoading.value = true
    }

    fun hideLoading() {
        Log.d("Loader", "Loading ended")
        isLoading.value = false
    }

    fun navigateTo(destination: Class<*>, bundle: Bundle? = null) {
        val context = getApplication<Application>().applicationContext
        val intent = Intent(context, destination).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            bundle?.let { putExtras(it) }
        }
        context.startActivity(intent)
    }
}

