package com.example.taskmaster.features.todo.search.data

import com.example.taskmaster.model.ToDoList
import com.example.taskmaster.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ISearchEnvironment {
    fun searchList(query: String): Flow<List<ToDoList>>
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}
