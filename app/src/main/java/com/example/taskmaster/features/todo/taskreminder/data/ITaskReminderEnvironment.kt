package com.example.taskmaster.features.todo.taskreminder.data

import com.example.taskmaster.model.TaskWithList
import com.example.taskmaster.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ITaskReminderEnvironment {
    fun notifyNotification(taskId: String): Flow<TaskWithList>
    fun snoozeReminder(taskId: String): Flow<TaskWithList>
    suspend fun completeReminder(taskId: String): Flow<TaskWithList>
    fun restartAllReminder(): Flow<List<ToDoTask>>
}
