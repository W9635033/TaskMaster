package com.example.taskmaster.features.host.ui

import com.example.taskmaster.model.Theme
import javax.annotation.concurrent.Immutable

@Immutable
data class HostState(val theme: Theme = Theme.SYSTEM)
