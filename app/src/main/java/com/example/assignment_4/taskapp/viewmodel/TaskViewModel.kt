package com.example.assignment_4.taskapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.assignment_4.taskapp.data.Task
import com.example.assignment_4.taskapp.repository.TaskRepository

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()
    val tasks: LiveData<List<Task>> = repository.getTasks()

    fun addTask(title: String) {
        if (title.isNotBlank()) {
            val task = Task(title = title)
            repository.addTask(task)
        }
    }

    fun removeTask(taskId: Long) {
        repository.removeTask(taskId)
    }

    fun clearCompleted() {
        repository.clearCompletedTasks()
    }
}