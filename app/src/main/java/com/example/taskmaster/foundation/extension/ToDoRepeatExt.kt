package com.example.taskmaster.foundation.extension

import com.example.taskmaster.R
import com.example.taskmaster.model.ToDoRepeat

fun ToDoRepeat.displayable(): Int {
    return when (this) {
        ToDoRepeat.NEVER -> R.string.todo_repeat_never
        ToDoRepeat.DAILY -> R.string.todo_repeat_daily
        ToDoRepeat.WEEKDAYS -> R.string.todo_repeat_weekdays
        ToDoRepeat.WEEKLY -> R.string.todo_repeat_weekly
        ToDoRepeat.MONTHLY -> R.string.todo_repeat_monthly
        ToDoRepeat.YEARLY -> R.string.todo_repeat_yearly
    }
}
