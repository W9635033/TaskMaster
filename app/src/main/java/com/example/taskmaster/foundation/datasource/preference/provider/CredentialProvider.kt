package com.example.taskmaster.foundation.datasource.preference.provider

import androidx.datastore.core.DataStore
import com.example.taskmaster.foundation.datasource.preference.model.CredentialPreference
import com.example.taskmaster.foundation.di.DiName
import com.example.taskmaster.model.Credential
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class CredentialProvider@Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val credentialDataStore: DataStore<CredentialPreference>,
) {

    fun getCredential(): Flow<Credential> {
        return credentialDataStore.data
            .map { Credential(it.token) }
            .catch { emit(Credential(token = "")) }
            .flowOn(dispatcher)
    }

    suspend fun setCredential(data: Credential) {
        withContext(dispatcher) {
            credentialDataStore.updateData {
                CredentialPreference(data.token)
            }
        }
    }

}
