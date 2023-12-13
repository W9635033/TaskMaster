package com.example.taskmaster.features.todo.taskreminder.ui

sealed class TaskReminderAction {
    data class AlarmShow(val taskId: String) : TaskReminderAction()
    data class NotificationCompleted(val taskId: String) : TaskReminderAction()
    data class NotificationSnooze(val taskId: String) : TaskReminderAction()
    object AppBootCompleted : TaskReminderAction()
}
