package com.padmakar.jetpackcomposeintroduction.core

import android.content.Intent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Base ViewModel that provides reusable event-handling logic
open class BaseViewModel : ViewModel() {
    // Loading state that can be used directly in composable
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Event Flow for one-time events like toasts, navigation
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    // Emit a loading state and update UI state as well
    suspend fun showLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
        _eventFlow.emit(UiEvent.ShowLoading(isLoading))
    }

    suspend fun showToast(message: String) {
        _eventFlow.emit(UiEvent.ShowToast(message))
    }

    suspend fun navigate(route: String) {
        _eventFlow.emit(UiEvent.Navigate(route))
    }

    suspend fun showError(message: String) {
        _eventFlow.emit(UiEvent.ShowError(message))
    }
    fun navigateToActivity(intent: Intent) {
        emitEvent(UiEvent.StartActivity(intent))
    }

    private fun emitEvent(event: UiEvent) {
        CoroutineScope(Dispatchers.Main).launch {
            _eventFlow.emit(event)
        }
    }
}