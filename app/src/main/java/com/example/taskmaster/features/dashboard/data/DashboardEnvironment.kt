package com.example.taskmaster.features.dashboard.data

import com.example.taskmaster.features.todo.taskreminder.data.TaskAlarmManager
import com.example.taskmaster.features.todo.taskreminder.data.TaskNotificationManager
import com.example.taskmaster.foundation.datasource.local.provider.ToDoTaskProvider
import com.example.taskmaster.foundation.datasource.preference.provider.UserProvider
import com.example.taskmaster.foundation.extension.getScheduledDueDate
import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.model.ToDoTask
import com.example.taskmaster.model.ToDoTaskDiff
import com.example.taskmaster.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DashboardEnvironment @Inject constructor(
    private val dateTimeProvider: DateTimeProvider,
    private val userProvider: UserProvider,
    private val toDoTaskProvider: ToDoTaskProvider,
    private val taskAlarmManager: TaskAlarmManager,
    private val notificationManager: TaskNotificationManager
) : IDashboardEnvironment {

    override fun getUser(): Flow<User> {
        return userProvider.getUser()
    }

    // TODO e2e
    override fun listenToDoTaskDiff(): Flow<ToDoTaskDiff> {
        var tasks: Map<String, ToDoTask> = mapOf()
        return toDoTaskProvider.getScheduledTasks()
            .distinctUntilChangedBy { newTasks -> newTasks.map { Triple(it.dueDate, it.repeat, it.status) } } // Consume when due date, repeat, and status have changes only
            .map { newTasks -> newTasks.associateBy({ it.id }, { it }) }
            .map { newTasks ->
                ToDoTaskDiff(
                    addedTask = newTasks - tasks.keys,
                    deletedTask = tasks - newTasks.keys,
                    modifiedTask = newTasks.filter { (key, value) -> key in tasks.keys && value != tasks[key] }
                )
                    .apply {
                        tasks = newTasks
                    }
            }
            .drop(1) // Skip initial value
            .onEach { todoTaskDiff ->
                todoTaskDiff.addedTask.forEach {
                    taskAlarmManager.scheduleTaskAlarm(it.value, it.value.getScheduledDueDate(dateTimeProvider.now()))
                }

                todoTaskDiff.modifiedTask.forEach {
                    taskAlarmManager.scheduleTaskAlarm(it.value, it.value.getScheduledDueDate(dateTimeProvider.now()))
                }

                todoTaskDiff.deletedTask.forEach {
                    taskAlarmManager.cancelTaskAlarm(it.value)
                    notificationManager.dismiss(it.value)
                }
            }
    }

}
