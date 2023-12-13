package com.example.taskmaster.features.todo.groupmenu.data

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import kotlinx.coroutines.flow.Flow

interface IGroupMenuEnvironment {
    val dateTimeProvider: DateTimeProvider
    suspend fun deleteGroup(groupId: String): Flow<Any>
    fun hasList(groupId: String): Flow<Boolean>
}
