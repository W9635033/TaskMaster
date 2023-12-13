package com.example.taskmaster.foundation.datasource.local.provider

import com.example.taskmaster.foundation.datasource.local.dao.ToDoTaskReadDao
import com.example.taskmaster.foundation.datasource.local.dao.ToDoTaskWriteDao
import com.example.taskmaster.foundation.datasource.local.mapper.toTask
import com.example.taskmaster.foundation.datasource.local.mapper.toTaskDb
import com.example.taskmaster.foundation.datasource.local.mapper.toToDoList
import com.example.taskmaster.foundation.di.DiName
import com.example.taskmaster.model.TaskWithList
import com.example.taskmaster.model.ToDoRepeat
import com.example.taskmaster.model.ToDoStatus
import com.example.taskmaster.model.ToDoTask
import com.example.taskmaster.model.ToDoTaskOverallCount
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Named


class ToDoTaskProvider @Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val toDoTaskWriteDao: ToDoTaskWriteDao,
    private val toDoTaskReadDao: ToDoTaskReadDao
) {

    fun getOverallCount(date: LocalDateTime): Flow<ToDoTaskOverallCount> {
        return toDoTaskReadDao.getTaskOverallCount(date)
            .flowOn(dispatcher)
    }

    fun getTaskWithListOrderByDueDate(): Flow<List<TaskWithList>> {
        return toDoTaskReadDao.getTaskWithListOrderByDueDate()
            .filterNotNull()
            .map { tasks ->
                tasks.map {
                    TaskWithList(it.list.toToDoList(), it.task.toTask())
                }
            }
            .flowOn(dispatcher)
    }

    fun getTaskWithListOrderByDueDateToday(date: LocalDateTime): Flow<List<TaskWithList>> {
        return toDoTaskReadDao.getTaskWithListOrderByDueDateToday(date)
            .filterNotNull()
            .map { tasks ->
                tasks.map {
                    TaskWithList(it.list.toToDoList(), it.task.toTask())
                }
            }
            .flowOn(dispatcher)
    }

    fun getTaskWithStepsById(taskId: String): Flow<ToDoTask> {
        return toDoTaskReadDao.getTaskWithStepsById(taskId)
            .filterNotNull()
            .map { it.toTask() }
            .flowOn(dispatcher)
    }

    fun getTaskWithListById(taskId: String): Flow<TaskWithList> {
        return toDoTaskReadDao.getTaskWithListById(taskId)
            .filterNotNull()
            .map { TaskWithList(it.list.toToDoList(), it.task.toTask()) }
            .flowOn(dispatcher)
    }

    fun searchTaskWithList(query: String): Flow<List<TaskWithList>> {
        return toDoTaskReadDao.searchTaskWithList(query)
            .map { tasks ->
                tasks.map {
                    TaskWithList(it.list.toToDoList(), it.task.toTask())
                }
            }
            .flowOn(dispatcher)
    }

    fun getScheduledTasks(): Flow<List<ToDoTask>> {
        return toDoTaskReadDao.getScheduledTasks()
            .map { it.toTask() }
            .flowOn(dispatcher)
    }

    suspend fun insertTask(data: List<ToDoTask>, listId: String) {
        withContext(dispatcher) {
            toDoTaskWriteDao.insertTask(data.toTaskDb(listId))
        }
    }

    suspend fun updateTaskDueDate(
        id: String,
        dueDateTime: LocalDateTime?,
        isDueDateTimeSet: Boolean,
        updatedAt: LocalDateTime
    ) {
        withContext(dispatcher) {
            toDoTaskWriteDao.updateTaskDueDate(id, dueDateTime, isDueDateTimeSet, updatedAt)
        }
    }

    suspend fun resetTaskDueDate(
        id: String,
        updatedAt: LocalDateTime
    ) {
        withContext(dispatcher) {
            toDoTaskWriteDao.resetTaskDueDate(
                id,
                null,
                false,
                ToDoRepeat.NEVER,
                updatedAt
            )
        }
    }

    suspend fun updateTaskRepeat(id: String, repeat: ToDoRepeat, updatedAt: LocalDateTime) {
        withContext(dispatcher) {
            toDoTaskWriteDao.updateTaskRepeat(id, repeat, updatedAt)
        }
    }

    suspend fun updateTaskStatus(id: String, status: ToDoStatus, completedAt: LocalDateTime?, updatedAt: LocalDateTime) {
        withContext(dispatcher) {
            toDoTaskWriteDao.updateTaskStatus(id, status, completedAt, updatedAt)
        }
    }

    suspend fun updateTaskNote(id: String, note: String, updatedAt: LocalDateTime) {
        withContext(dispatcher) {
            toDoTaskWriteDao.updateTaskNote(id, note, updatedAt)
        }
    }

    suspend fun updateTaskName(id: String, name: String, updatedAt: LocalDateTime) {
        withContext(dispatcher) {
            toDoTaskWriteDao.updateTaskName(id, name, updatedAt)
        }
    }

    suspend fun deleteTaskById(id: String) {
        withContext(dispatcher) {
            toDoTaskWriteDao.deleteTaskById(id)
        }
    }

}
