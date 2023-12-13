package com.example.taskmaster.features.todo.scheduled.data

import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.foundation.wrapper.IdProvider
import com.example.taskmaster.model.TaskWithList
import com.example.taskmaster.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface IScheduledEnvironment {
    val idProvider: IdProvider
    val dateTimeProvider: DateTimeProvider
    fun getToDoTaskWithStepsOrderByDueDateWithList(maxDate: LocalDateTime? = null): Flow<List<TaskWithList>>
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}
