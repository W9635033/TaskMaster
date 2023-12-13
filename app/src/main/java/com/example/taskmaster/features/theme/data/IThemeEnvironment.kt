package com.example.taskmaster.features.theme.data

import com.example.taskmaster.model.Theme
import kotlinx.coroutines.flow.Flow

interface IThemeEnvironment {
    fun getTheme(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
}
