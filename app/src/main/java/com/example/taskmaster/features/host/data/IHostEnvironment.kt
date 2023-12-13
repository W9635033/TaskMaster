package com.example.taskmaster.features.host.data

import com.example.taskmaster.model.Theme
import kotlinx.coroutines.flow.Flow

interface IHostEnvironment {
    fun getTheme(): Flow<Theme>
}
