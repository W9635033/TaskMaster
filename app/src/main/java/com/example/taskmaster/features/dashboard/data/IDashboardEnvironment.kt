package com.example.taskmaster.features.dashboard.data

import com.example.taskmaster.model.ToDoTaskDiff
import com.example.taskmaster.model.User
import kotlinx.coroutines.flow.Flow

interface IDashboardEnvironment {
    fun getUser(): Flow<User>
    fun listenToDoTaskDiff(): Flow<ToDoTaskDiff>
}
