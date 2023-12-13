package com.example.taskmaster.features.todo.group.ui

import androidx.compose.ui.text.input.TextFieldValue
import javax.annotation.concurrent.Immutable

@Immutable
data class CreateGroupState(
    val groupName: TextFieldValue = TextFieldValue()
)
