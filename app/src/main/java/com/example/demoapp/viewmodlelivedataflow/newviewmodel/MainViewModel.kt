package com.example.demoapp.viewmodlelivedataflow.newviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.viewmodlelivedataflow.model.Article

import com.example.demoapp.viewmodlelivedataflow.model.NewsDataModel
import com.example.demoapp.viewmodlelivedataflow.model.UserInformationModel
import com.example.demoapp.viewmodlelivedataflow.repository.NewsArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsArticleRepository ) : ViewModel() {

    val newsFlow by lazy {
        MutableSharedFlow<List<Article>>()
    }



//        init {
//            viewModelScope.launch(Dispatchers.IO) {
//                repository.getArticle("yudiz")
//            }
//        }
//    val article : LiveData<NewsDataModel>
//        get() = repository.newsArticle
//    val article : MutableSharedFlow<List<Article>>
//        get() = repository.newsArticle
//    fun newsDetails(name : String){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getArticle(name)
//        }
//    }
//
//    val article : LiveData<NewsDataModel>
//    get() = repository.newsArticle




    val userData : MutableLiveData<UserInformationModel> = MutableLiveData<UserInformationModel>()

    fun insertUserInformation(userInformation: UserInformationModel){
        userData.value = userInformation
    }

}