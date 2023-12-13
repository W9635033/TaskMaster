package com.example.taskmaster.foundation.extension

import androidx.compose.ui.graphics.Color
import com.example.taskmaster.foundation.theme.ListBlue
import com.example.taskmaster.foundation.theme.ListBrown
import com.example.taskmaster.foundation.theme.ListGreen
import com.example.taskmaster.foundation.theme.ListOrange
import com.example.taskmaster.foundation.theme.ListPurple
import com.example.taskmaster.foundation.theme.ListRed
import com.example.taskmaster.foundation.theme.ListYellow
import com.example.taskmaster.model.ToDoColor

fun ToDoColor.toColor() = when (this) {
    ToDoColor.BLUE -> ListBlue
    ToDoColor.RED -> ListRed
    ToDoColor.GREEN -> ListGreen
    ToDoColor.YELLOW -> ListYellow
    ToDoColor.ORANGE -> ListOrange
    ToDoColor.PURPLE -> ListPurple
    ToDoColor.BROWN -> ListBrown
}

fun Color.toToDoColor() = when (this) {
    ListRed -> ToDoColor.RED
    ListGreen -> ToDoColor.GREEN
    ListYellow -> ToDoColor.YELLOW
    ListOrange -> ToDoColor.ORANGE
    ListPurple -> ToDoColor.PURPLE
    ListBrown -> ToDoColor.BROWN
    else -> ToDoColor.BLUE
}

