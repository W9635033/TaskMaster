package com.example.taskmaster.features.todo.step.ui

sealed class StepEffect {
    data class ScrollTo(val position: Int) : StepEffect()
}
