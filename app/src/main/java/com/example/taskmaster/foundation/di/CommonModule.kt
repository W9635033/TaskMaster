package com.example.taskmaster.foundation.di

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.foundation.wrapper.DateTimeProviderImpl
import com.example.taskmaster.foundation.wrapper.IdProvider
import com.example.taskmaster.foundation.wrapper.IdProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideIdProvider(): IdProvider {
        return IdProviderImpl()
    }

    @Singleton
    @Provides
    fun provideDateTimeProvider(): DateTimeProvider {
        return DateTimeProviderImpl()
    }

}
