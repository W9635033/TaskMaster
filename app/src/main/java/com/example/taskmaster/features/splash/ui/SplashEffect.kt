package com.example.taskmaster.features.splash.ui

sealed class SplashEffect {
    object NavigateToDashboard : SplashEffect()
    object NavigateToLogin : SplashEffect()
}
