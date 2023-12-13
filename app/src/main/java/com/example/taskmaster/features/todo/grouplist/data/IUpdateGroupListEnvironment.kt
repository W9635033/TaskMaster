package com.example.taskmaster.features.todo.grouplist.data

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.model.GroupIdWithList
import kotlinx.coroutines.flow.Flow

interface IUpdateGroupListEnvironment {
    val dateTimeProvider: DateTimeProvider
    fun getListWithUnGroupList(groupId: String): Flow<List<GroupIdWithList>>
    suspend fun updateList(data: List<GroupIdWithList>)
}
