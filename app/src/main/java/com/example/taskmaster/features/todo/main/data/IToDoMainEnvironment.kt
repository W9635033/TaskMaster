package com.example.taskmaster.features.todo.main.data

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.model.ToDoGroup
import com.example.taskmaster.model.ToDoList
import com.example.taskmaster.model.ToDoTaskOverallCount
import kotlinx.coroutines.flow.Flow

interface IToDoMainEnvironment {
    val dateTimeProvider: DateTimeProvider
    fun getGroup(): Flow<List<ToDoGroup>>
    fun getOverallCount(): Flow<ToDoTaskOverallCount>
    suspend fun deleteList(list: ToDoList)
}
