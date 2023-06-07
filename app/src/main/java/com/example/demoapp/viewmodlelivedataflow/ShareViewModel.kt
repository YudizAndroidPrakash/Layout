package com.example.demoapp.viewmodlelivedataflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    val userData : MutableLiveData<UserInformation> = MutableLiveData<UserInformation>()

    fun insertUserInformation(userInformation: UserInformation){
        userData.value = userInformation
    }
}