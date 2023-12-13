package com.example.taskmaster.features.dashboard.ui

import androidx.compose.runtime.Immutable
import com.example.taskmaster.model.User

@Immutable
data class DashboardState(val user: User = User())
