package com.example.demoapp.roomdatabase.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserRegistration(
    @PrimaryKey(autoGenerate = true) val id : Long,
    val Name : String,
    val Email : String,
    val Mobile : Long,
    val Password : String
)
