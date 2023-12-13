package com.example.taskmaster.features.localized.setting.ui

import com.example.taskmaster.model.Language

sealed class LocalizedEffect {
    data class ApplyLanguage(val language: Language) : LocalizedEffect()
}
