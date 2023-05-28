package com.example.demoapp.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.demoapp.roomdatabase.table.Task
import com.example.demoapp.roomdatabase.AppRoomDatabase
import com.example.demoapp.roomdatabase.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    init {
        val dao = AppRoomDatabase.getObject(application).taskDao()
        repository = TaskRepository(dao)
    }


 fun allUserTask(userId: Long): LiveData<List<Task>> {
        return repository.userTask(userId)
    }

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTask(task)
    }

    fun updateTask(taskTitle :String,taskDescription : String,taskId : Long) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTask(taskTitle,taskDescription,taskId)
    }


}