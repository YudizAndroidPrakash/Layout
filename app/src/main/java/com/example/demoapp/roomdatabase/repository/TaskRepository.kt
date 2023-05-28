package com.example.demoapp.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.demoapp.roomdatabase.dao.TaskDAO
import com.example.demoapp.roomdatabase.table.Task

class TaskRepository(private val taskDao : TaskDAO) {


  fun userTask(userId : Long) : LiveData<List<Task>>{
        val allTask  : LiveData<List<Task>> =   taskDao.getAllTask(userId)
        return  allTask
    }


    //insert task into the table
    suspend fun insertTask(task: Task){
        taskDao.insertTask(task)
    }

    //delete  data
    suspend fun deleteTask(task : Task){
        taskDao.deleteTask(task)
    }

    suspend fun updateTask(taskTitle :String,taskDescription : String,taskId : Long){
        taskDao.updateTask(taskTitle,taskDescription,taskId)
    }


}