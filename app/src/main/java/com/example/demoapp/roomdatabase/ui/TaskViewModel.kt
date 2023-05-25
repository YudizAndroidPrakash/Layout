package com.example.demoapp.roomdatabase.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val allTask: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val dao = AppRoomDatabase.getObject(application).taskDao()
        repository = TaskRepository(dao)
        allTask = repository.allTask
    }

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTask(task)
    }
}