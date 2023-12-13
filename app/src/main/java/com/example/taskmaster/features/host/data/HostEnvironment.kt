package com.example.taskmaster.features.host.data

import com.example.taskmaster.foundation.datasource.preference.provider.ThemeProvider
import com.example.taskmaster.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HostEnvironment @Inject constructor(
    private val themeProvider: ThemeProvider
) : IHostEnvironment {

    override fun getTheme(): Flow<Theme> {
        return themeProvider.getTheme()
    }

}
