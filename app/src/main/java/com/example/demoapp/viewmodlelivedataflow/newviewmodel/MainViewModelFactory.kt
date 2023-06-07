package com.example.demoapp.viewmodlelivedataflow.newviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.demoapp.viewmodlelivedataflow.repository.NewsArticleRepository

class MainViewModelFactory(private val repository: NewsArticleRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}