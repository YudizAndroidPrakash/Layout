package com.example.demoapp.roomdatabase.ui

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDAO  {

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE Task SET taskTitle=:taskTitle,taskDescription=:taskDescription WHERE taskId=:taskId")
   suspend fun updateTask(taskTitle :String,taskDescription : String,taskId : Long)


    @Query("SELECT * FROM Task where userID=:userId")
    fun getAllTask(userId : Long) : LiveData<List<Task>>

}