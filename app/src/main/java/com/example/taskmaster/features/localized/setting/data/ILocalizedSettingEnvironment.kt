package com.example.taskmaster.features.localized.setting.data

import com.example.taskmaster.model.Language
import kotlinx.coroutines.flow.Flow

interface ILocalizedSettingEnvironment {
    fun getLanguage(): Flow<Language>
    suspend fun setLanguage(language: Language)
}

