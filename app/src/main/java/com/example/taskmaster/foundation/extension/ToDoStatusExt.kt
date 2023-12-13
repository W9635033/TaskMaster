package com.example.taskmaster.foundation.extension

import com.example.taskmaster.model.ToDoStatus

fun ToDoStatus.toggle(): ToDoStatus {
    return when (this) {
        ToDoStatus.IN_PROGRESS -> ToDoStatus.COMPLETE
        ToDoStatus.COMPLETE -> ToDoStatus.IN_PROGRESS
    }
}
