package com.clcmo.core.presentation

sealed class UIState {
    object Complete : UIState()
    object Loading : UIState()
    class Error(val errorMessage: String) : UIState()
    object NoInternet : UIState()
}