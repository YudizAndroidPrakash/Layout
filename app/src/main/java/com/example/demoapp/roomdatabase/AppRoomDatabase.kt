package com.example.demoapp.roomdatabase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapp.roomdatabase.dao.TaskDAO
import com.example.demoapp.roomdatabase.dao.UserRegistrationDAO
import com.example.demoapp.roomdatabase.table.Task
import com.example.demoapp.roomdatabase.table.UserRegistration

@Database(entities = [UserRegistration::class, Task::class], version = 2)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserRegistrationDAO
    abstract fun taskDao() : TaskDAO


    companion object {
        @Volatile
        private var dbObject: AppRoomDatabase? = null
       fun getObject(context: Context): AppRoomDatabase {
           return dbObject ?: synchronized(this){
             val  dbObjectOne =  Room.databaseBuilder(
                   context.applicationContext,
                   AppRoomDatabase::class.java, "app_database_demo"
               ).build()
               dbObject =dbObjectOne
               dbObjectOne
           }
        }


    }
}