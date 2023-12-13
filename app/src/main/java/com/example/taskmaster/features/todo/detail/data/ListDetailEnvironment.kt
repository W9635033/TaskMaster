package com.example.taskmaster.features.todo.detail.data

import com.example.taskmaster.foundation.datasource.local.model.ToDoGroupDb
import com.example.taskmaster.foundation.datasource.local.provider.ToDoListProvider
import com.example.taskmaster.foundation.datasource.local.provider.ToDoTaskProvider
import com.example.taskmaster.foundation.extension.OnResolveDuplicateName
import com.example.taskmaster.foundation.extension.duplicateNameResolver
import com.example.taskmaster.foundation.extension.resolveListName
import com.example.taskmaster.foundation.extension.toggleStatusHandler
import com.example.taskmaster.foundation.wrapper.DateTimeProvider
import com.example.taskmaster.foundation.wrapper.IdProvider
import com.example.taskmaster.model.ToDoList
import com.example.taskmaster.model.ToDoTask
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@FlowPreview
class ListDetailEnvironment @Inject constructor(
    private val toDoListProvider: ToDoListProvider,
    private val toDoTaskProvider: ToDoTaskProvider,
    override val idProvider: IdProvider,
    override val dateTimeProvider: DateTimeProvider,
) : IListDetailEnvironment {

    override fun getListWithTasksById(listId: String): Flow<ToDoList> {
        return toDoListProvider.getListWithTasksById(listId)
    }

    override suspend fun createList(list: ToDoList): Flow<ToDoList> {
        val process: OnResolveDuplicateName = { newName ->
            toDoListProvider.insertList(
                listOf(list.copy(name = newName)),
                ToDoGroupDb.DEFAULT_ID
            )
        }

        return duplicateNameResolver(
            updateName = { process(list.name) },
            onDuplicate = { resolveListName(list.name, toDoListProvider.getList(), process) }
        )
            .flatMapConcat { toDoListProvider.getListWithTasksById(list.id) }
    }

    override suspend fun updateList(list: ToDoList): Flow<Any> {
        val currentDate = dateTimeProvider.now()
        val process: OnResolveDuplicateName = { newName ->
            toDoListProvider.updateListNameAndColor(list.copy(name = newName), currentDate)
        }

        return duplicateNameResolver(
            updateName = { process(list.name) },
            onDuplicate = { resolveListName(list.name, toDoListProvider.getList(), process) }
        )
    }

    override suspend fun createTask(taskName: String, listId: String) {
        val currentDate = dateTimeProvider.now()

        toDoTaskProvider.insertTask(
            listOf(
                ToDoTask(
                    id = idProvider.generate(),
                    name = taskName,
                    createdAt = currentDate,
                    updatedAt = currentDate
                )
            ),
            listId
        )
    }

    override suspend fun toggleTaskStatus(toDoTask: ToDoTask) {
        val currentDate = dateTimeProvider.now()
        toDoTask.toggleStatusHandler(
            currentDate,
            { completedAt, newStatus ->
                toDoTaskProvider.updateTaskStatus(toDoTask.id, newStatus, completedAt, currentDate)
            },
            { nextDueDate ->
                toDoTaskProvider.updateTaskDueDate(toDoTask.id, nextDueDate, toDoTask.isDueDateTimeSet, currentDate)
            }
        )
    }

    override suspend fun deleteTask(task: ToDoTask) {
        toDoTaskProvider.deleteTaskById(task.id)
    }
}
