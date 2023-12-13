package com.example.taskmaster.foundation.datasource.preference.mapper

import com.example.taskmaster.foundation.datasource.preference.model.LanguagePreference
import com.example.taskmaster.model.Language

fun Language.toLanguagePreference(): LanguagePreference {
    return when (this) {
        Language.ENGLISH -> LanguagePreference.ENGLISH
        Language.INDONESIA -> LanguagePreference.INDONESIA
    }
}

fun LanguagePreference.toLanguage(): Language {
    return when (this) {
        LanguagePreference.ENGLISH -> Language.ENGLISH
        LanguagePreference.INDONESIA -> Language.INDONESIA
    }
}
