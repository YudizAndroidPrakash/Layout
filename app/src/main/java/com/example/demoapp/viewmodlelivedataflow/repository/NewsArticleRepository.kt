package com.example.demoapp.viewmodlelivedataflow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.viewmodlelivedataflow.api.NewsServiceProvider
import com.example.demoapp.viewmodlelivedataflow.model.NewsDataModel

class NewsArticleRepository(private val newsServiceProvider: NewsServiceProvider) {
   private val newsArticleMutableLiveData = MutableLiveData<NewsDataModel>()

   val  newsArticle : LiveData<NewsDataModel>
   get() = newsArticleMutableLiveData

    suspend fun getArticle(name : String) {
        val articleResult = newsServiceProvider.getArticles(name)
        if(articleResult?.body() != null){
            newsArticleMutableLiveData.postValue(articleResult.body())
        }
    }

}