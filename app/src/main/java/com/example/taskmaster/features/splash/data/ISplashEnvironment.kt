package com.example.taskmaster.features.splash.data

import com.example.taskmaster.model.Credential
import kotlinx.coroutines.flow.Flow

interface ISplashEnvironment {
    fun getCredential(): Flow<Credential>
}
