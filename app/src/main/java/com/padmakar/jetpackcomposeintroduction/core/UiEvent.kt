package com.padmakar.jetpackcomposeintroduction.core

import android.content.Intent

sealed class UiEvent {
    data class ShowLoading(val isLoading: Boolean) : UiEvent()
    data class ShowToast(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class ShowError(val message: String) : UiEvent()
    data class StartActivity(val intent: Intent) : UiEvent()
}