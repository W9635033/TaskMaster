package com.example.taskmaster.features.todo.detail.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.example.taskmaster.foundation.theme.ListBlue
import com.example.taskmaster.foundation.theme.ListBrown
import com.example.taskmaster.foundation.theme.ListGreen
import com.example.taskmaster.foundation.theme.ListOrange
import com.example.taskmaster.foundation.theme.ListPurple
import com.example.taskmaster.foundation.theme.ListRed
import com.example.taskmaster.foundation.theme.ListYellow
import com.example.taskmaster.foundation.wrapper.DateTimeProviderImpl
import com.example.taskmaster.model.ToDoColor
import com.example.taskmaster.model.ToDoList
import com.example.taskmaster.model.ToDoTask

@Immutable
data class ListDetailState(
    val list: ToDoList = ToDoList(
        createdAt = DateTimeProviderImpl().now(),
        updatedAt = DateTimeProviderImpl().now()
    ),
    val newListName: String = "",
    val colors: List<ColorItem> = initialColors(),
    val taskName: TextFieldValue = TextFieldValue()
) {
    val listDisplayable = list.toToDoListState()
    val validListName = newListName.isNotBlank()
    val validTaskName = taskName.text.isNotBlank()

    companion object {
        private fun initialColors(): List<ColorItem> {
            return listOf(
                ColorItem(
                    color = ListRed,
                    applied = false
                ),
                ColorItem(
                    color = ListOrange,
                    applied = false
                ),
                ColorItem(
                    color = ListYellow,
                    applied = false
                ),
                ColorItem(
                    color = ListGreen,
                    applied = false
                ),
                ColorItem(
                    color = ListBlue,
                    applied = true
                ),
                ColorItem(
                    color = ListPurple,
                    applied = false
                ),
                ColorItem(
                    color = ListBrown,
                    applied = false
                )
            )
        }
    }
}

data class ToDoListState(
    val id: String = "",
    val name: String = "",
    val color: ToDoColor = ToDoColor.BLUE,
    val tasks: List<ToDoTaskItem> = listOf()
)

sealed class ToDoTaskItem {
    data class CompleteHeader(val id: String = "CompleteHeader") : ToDoTaskItem()

    data class Complete(
        val toDoTask: ToDoTask
    ) : ToDoTaskItem()

    data class InProgress(
        val toDoTask: ToDoTask
    ) : ToDoTaskItem()
}

data class ColorItem(
    val color: Color,
    val applied: Boolean
)
