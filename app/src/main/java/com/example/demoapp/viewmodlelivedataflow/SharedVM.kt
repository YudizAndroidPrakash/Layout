package com.example.demoapp.viewmodlelivedataflow

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class SharedVM(private val repository: UserInformationRepository) : ViewModel() {

//    val userData : MutableLiveData<<UserInformation> = MutableLiveData<UserInformation>()

//    fun insertUserInformation(userInformation: UserInformation) = viewModelScope.launch {
//        userInformationRepository.insertUserInformation(userInformation)
//    }


//

    fun getAllNews(topic : String) : ArrayList<Articles>? {

        var newsList : ArrayList<Articles>? = null
//        viewModelScope.launch {
//            val response = repository.getAllNews(topic)
//            if(response.isSuccessful){
//               return response?.body()
//            }else {
//                Log.e("response fail",response.message())
//            }
//        }
        viewModelScope.launch {
            val response = repository.getAllNews(topic)
            response.body()

            if (response.isSuccessful) {
//                    newsList.postValue(response.body()?.articles)
                newsList = response.body()?.articles
            } else {
                Log.e("response", response.message())
            }
        }
        return newsList


    }
}