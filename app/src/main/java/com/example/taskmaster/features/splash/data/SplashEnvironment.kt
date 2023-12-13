package com.example.taskmaster.features.splash.data

import com.example.taskmaster.foundation.datasource.preference.provider.CredentialProvider
import com.example.taskmaster.model.Credential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashEnvironment @Inject constructor(
    private val credentialProvider: CredentialProvider
) : ISplashEnvironment {

    override fun getCredential(): Flow<Credential> {
        return credentialProvider.getCredential()
    }

}
