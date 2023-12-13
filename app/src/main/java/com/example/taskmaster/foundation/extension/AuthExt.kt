package com.example.taskmaster.foundation.extension

import com.example.taskmaster.model.Credential

fun Credential.isLoggedIn() = token.isNotBlank()
