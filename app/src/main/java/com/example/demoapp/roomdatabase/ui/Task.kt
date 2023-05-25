package com.example.demoapp.roomdatabase.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Int = 0,
    val taskTitle : String,
    val taskDescription: String,
    val userID : Long
){

}