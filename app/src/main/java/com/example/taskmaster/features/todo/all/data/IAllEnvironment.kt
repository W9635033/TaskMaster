package com.example.taskmaster.features.todo.all.data

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.model.ToDoList
import com.example.taskmaster.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface IAllEnvironment {
    val dateTimeProvider: DateTimeProvider
    fun getList(): Flow<List<ToDoList>>
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}
