package com.example.taskmaster.foundation.di

import android.content.Context
import com.example.taskmaster.foundation.datasource.local.ToDoDatabase
import com.example.taskmaster.foundation.datasource.local.dao.ToDoGroupReadDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoGroupWriteDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoListReadDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoListWriteDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoStepReadDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoStepWriteDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoTaskReadDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoTaskWriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideToDoGroupWriteDao(@ApplicationContext context: Context): ToDoGroupWriteDao {
        return ToDoDatabase.getInstance(context)
            .toDoGroupWriteDao()
    }

    @Singleton
    @Provides
    fun provideToDoListWriteDao(@ApplicationContext context: Context): ToDoListWriteDao {
        return ToDoDatabase.getInstance(context)
            .toDoListWriteDao()
    }

    @Singleton
    @Provides
    fun provideToDoTaskWriteDao(@ApplicationContext context: Context): ToDoTaskWriteDao {
        return ToDoDatabase.getInstance(context)
            .toDoTaskWriteDao()
    }

    @Singleton
    @Provides
    fun provideToDoStepWriteDao(@ApplicationContext context: Context): ToDoStepWriteDao {
        return ToDoDatabase.getInstance(context)
            .toDoStepWriteDao()
    }

    @Singleton
    @Provides
    fun provideToDoGroupReadDao(@ApplicationContext context: Context): ToDoGroupReadDao {
        return ToDoDatabase.getInstance(context)
            .toDoGroupReadDao()
    }

    @Singleton
    @Provides
    fun provideToDoListReadDao(@ApplicationContext context: Context): ToDoListReadDao {
        return ToDoDatabase.getInstance(context)
            .toDoListReadDao()
    }

    @Singleton
    @Provides
    fun provideToDoTaskReadDao(@ApplicationContext context: Context): ToDoTaskReadDao {
        return ToDoDatabase.getInstance(context)
            .toDoTaskReadDao()
    }

    @Singleton
    @Provides
    fun provideToDoStepReadDao(@ApplicationContext context: Context): ToDoStepReadDao {
        return ToDoDatabase.getInstance(context)
            .toDoStepReadDao()
    }

}
