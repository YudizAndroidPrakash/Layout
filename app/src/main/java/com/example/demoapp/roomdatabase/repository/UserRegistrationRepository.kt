package com.example.demoapp.roomdatabase.repository


import com.example.demoapp.roomdatabase.dao.UserRegistrationDAO
import com.example.demoapp.roomdatabase.table.UserRegistration

class UserRegistrationRepository(private val registratrionDao : UserRegistrationDAO) {



    //registration user
        suspend fun insertUser(userRegistration: UserRegistration){
            registratrionDao.insertUser(userRegistration)
        }
}