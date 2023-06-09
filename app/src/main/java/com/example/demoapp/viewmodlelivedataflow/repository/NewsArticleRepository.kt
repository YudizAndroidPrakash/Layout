package com.example.demoapp.viewmodlelivedataflow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.viewmodlelivedataflow.api.NewsServiceProvider
import com.example.demoapp.viewmodlelivedataflow.model.Article
import com.example.demoapp.viewmodlelivedataflow.model.NewsDataModel
import kotlinx.coroutines.flow.MutableSharedFlow

class NewsArticleRepository(private val newsServiceProvider: NewsServiceProvider) {
//   private val newsArticleMutableLiveData = MutableLiveData<NewsDataModel>()
    private val newsArticleMutableLiveData = MutableSharedFlow<List<Article>>()

   val  newsArticle : MutableSharedFlow<List<Article>>
   get() = newsArticleMutableLiveData

    suspend fun getArticle(name : String) {
        val articleResult = newsServiceProvider.getArticles(name)
        if(articleResult?.body() != null){
            newsArticleMutableLiveData.emit(articleResult.body()!!.articles)
        }
    }

}