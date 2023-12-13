package com.example.taskmaster.features.todo.group.data

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.foundation.wrapper.IdProvider
import com.example.taskmaster.model.ToDoGroup
import kotlinx.coroutines.flow.Flow

interface ICreateGroupEnvironment {
    val idProvider: IdProvider
    val dateTimeProvider: DateTimeProvider
    suspend fun getGroup(groupId: String): Flow<ToDoGroup>
    suspend fun createGroup(name: String): Flow<String>
    suspend fun renameGroup(groupId: String, name: String): Flow<Any>
}
