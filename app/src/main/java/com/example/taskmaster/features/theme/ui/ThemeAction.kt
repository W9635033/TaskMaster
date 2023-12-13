package com.example.taskmaster.features.theme.ui

sealed class ThemeAction {
    data class SelectTheme(val selected: ThemeItem) : ThemeAction()
}
