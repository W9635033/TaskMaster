package com.example.taskmaster.features.todo.taskreminder.di

import com.example.taskmaster.features.todo.taskreminder.data.ITaskReminderEnvironment
import com.example.taskmaster.features.todo.taskreminder.data.TaskReminderEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TaskReminderModule {

    @Binds
    abstract fun provideEnvironment(
        environment: TaskReminderEnvironment
    ): ITaskReminderEnvironment

}
