package com.example.taskmaster.model

import java.time.LocalDateTime

data class ToDoGroup(
    val id: String = "",
    val name: String = "",
    val lists: List<ToDoList> = listOf(),
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
