package com.example.taskmaster.features.splash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmaster.foundation.viewmodel.HandleEffect
import com.example.taskmaster.runtime.navigation.AuthFlow
import com.example.taskmaster.runtime.navigation.HomeFlow
import com.example.taskmaster.runtime.navigation.MainFlow

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
) {
    HandleEffect(viewModel) {
        when (it) {
            SplashEffect.NavigateToDashboard -> {
                navController.navigate(HomeFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
            SplashEffect.NavigateToLogin -> {
                navController.navigate(AuthFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}
