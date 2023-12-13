package com.example.taskmaster.features.todo.detail.data

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.foundation.wrapper.IdProvider
import com.example.taskmaster.model.ToDoList
import com.example.taskmaster.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface IListDetailEnvironment {
    val idProvider: IdProvider
    val dateTimeProvider: DateTimeProvider
    fun getListWithTasksById(listId: String): Flow<ToDoList>
    suspend fun createList(list: ToDoList): Flow<ToDoList>
    suspend fun updateList(list: ToDoList): Flow<Any>
    suspend fun createTask(taskName: String, listId: String)
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}
