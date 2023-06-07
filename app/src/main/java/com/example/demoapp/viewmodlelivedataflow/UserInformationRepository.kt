package com.example.demoapp.viewmodlelivedataflow

import androidx.lifecycle.MutableLiveData

class UserInformationRepository(private val newsService: NewsService) {
//    fun getUserData(): MutableLiveData<List<UserInformation>> {
//        return userInformationDao.getUserInformation()
//
//    }
//
//    suspend fun insertUserInformation(userInformation: UserInformation) {
//        newsService.insertUserInformation(userInformation)
//    }
// val userInformationList :  MutableLiveData<UserInformation> = newsService.getUserInformation()
//    suspend fun insertUserInformation(userInformation: UserInformation)


  suspend fun getAllNews(topic : String) = newsService.newDetails(topic)

}