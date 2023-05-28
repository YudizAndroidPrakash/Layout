package com.example.demoapp.roomdatabase.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demoapp.roomdatabase.table.UserRegistration

@Dao
interface UserRegistrationDAO {
        @Insert
        suspend fun insertUser(user: UserRegistration)

        @Update
        suspend fun updateUser(user: UserRegistration)

        @Delete
        suspend fun deleteUser(user: UserRegistration)


        @Query("select * from UserRegistration")
        fun getUSer(): LiveData<List<UserRegistration>>


        @Query("select * from UserRegistration where Email=:email and Password=:password")
        fun getUserDetail(email : String,password : String) : List<UserRegistration>



}