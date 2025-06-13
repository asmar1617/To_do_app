package com.example.assignment_4.taskapp.data

data class Task(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val isCompleted: Boolean = false
)