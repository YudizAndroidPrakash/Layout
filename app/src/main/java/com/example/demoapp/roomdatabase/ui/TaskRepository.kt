package com.example.demoapp.roomdatabase.ui

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao :TaskDAO) {

    val allTask  : LiveData<List<Task>> = taskDao.getAllTask()
    //insert task into the table
    suspend fun insertTask(task: Task){
        taskDao.insertTask(task)
    }

    //delete  data
    suspend fun deleteTask(task : Task){
        taskDao.deleteTask(task)
    }


}