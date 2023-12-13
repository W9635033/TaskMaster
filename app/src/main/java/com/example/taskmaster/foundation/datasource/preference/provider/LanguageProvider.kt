package com.example.taskmaster.foundation.datasource.preference.provider

import androidx.datastore.core.DataStore
import com.example.taskmaster.foundation.datasource.preference.mapper.toLanguage
import com.example.taskmaster.foundation.datasource.preference.mapper.toLanguagePreference
import com.example.taskmaster.foundation.datasource.preference.model.LanguagePreference
import com.example.taskmaster.foundation.di.DiName
import com.example.taskmaster.model.Language
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class LanguageProvider @Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val languageDataStore: DataStore<LanguagePreference>,
) {

    fun getLanguage(): Flow<Language> {
        return languageDataStore.data.map { it.toLanguage() }
            .catch { emit(Language.ENGLISH) }
            .flowOn(dispatcher)
    }

    suspend fun setLanguage(data: Language) {
        withContext(dispatcher) {
            languageDataStore.updateData {
                data.toLanguagePreference()
            }
        }
    }

}
