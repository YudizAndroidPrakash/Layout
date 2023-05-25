package com.example.demoapp.roomdatabase.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRegistrationViewModel(application: Application) : AndroidViewModel(application){
    var repository : UserRegistrationRepository
    var selectedData :  LiveData<List<UserRegistration>>?  = null


    init{
        val dao = AppRoomDatabase.getObject(application).userDao()
        repository = UserRegistrationRepository(dao)
    }

    fun insertUser(userRegistration: UserRegistration) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(userRegistration)
    }

//    fun getUserDetail(email : String,password : String) : LiveData<List<UserRegistration>>? {
//
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getTheUserDetail(email,password)
//            selectedData = repository.selectedData!!
//
//        }
//        return selectedData
//    }



}