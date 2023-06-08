package com.example.demoapp.viewmodlelivedataflow.newviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.viewmodlelivedataflow.model.Article
import com.example.demoapp.viewmodlelivedataflow.repository.NewsArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NewsViewModel (private val repository: NewsArticleRepository) : ViewModel() {

    val article : MutableSharedFlow<List<Article>>
        get() = repository.newsArticle
    fun newsDetails(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getArticle(name)
        }
    }

}