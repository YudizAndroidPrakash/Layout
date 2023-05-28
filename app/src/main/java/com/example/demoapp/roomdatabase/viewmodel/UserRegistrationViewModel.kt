package com.example.demoapp.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.roomdatabase.table.UserRegistration
import com.example.demoapp.roomdatabase.AppRoomDatabase
import com.example.demoapp.roomdatabase.repository.UserRegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRegistrationViewModel(application: Application) : AndroidViewModel(application){
  private  var repository : UserRegistrationRepository
//    var selectedData :  LiveData<List<UserRegistration>>?  = null


    init{
        val dao = AppRoomDatabase.getObject(application).userDao()
        repository = UserRegistrationRepository(dao)
    }

    fun insertUser(userRegistration: UserRegistration) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(userRegistration)
    }




}