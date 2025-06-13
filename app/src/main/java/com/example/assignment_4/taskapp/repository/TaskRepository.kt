package com.example.assignment_4.taskapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment_4.taskapp.data.Task


class TaskRepository {

    private val taskList = mutableListOf<Task>()
    private val tasksLiveData = MutableLiveData<List<Task>>(taskList)

    fun getTasks(): LiveData<List<Task>> = tasksLiveData

    fun addTask(task: Task) {
        taskList.add(task)
        tasksLiveData.value = taskList.toList()
    }

    fun removeTask(taskId: Long) {
        taskList.removeAll { it.id == taskId }
        tasksLiveData.value = taskList.toList()
    }

    fun clearCompletedTasks() {
        taskList.removeAll { it.isCompleted }
        tasksLiveData.value = taskList.toList()
    }
}