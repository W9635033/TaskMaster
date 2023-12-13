package com.example.taskmaster.foundation.wrapper

import java.time.LocalDateTime

interface DateTimeProvider {
    fun now(): LocalDateTime
}

class DateTimeProviderImpl : DateTimeProvider {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
