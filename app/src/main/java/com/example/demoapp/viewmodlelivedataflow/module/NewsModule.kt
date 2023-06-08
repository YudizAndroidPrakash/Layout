package com.example.demoapp.viewmodlelivedataflow.module

import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.viewmodlelivedataflow.Car
import com.example.demoapp.viewmodlelivedataflow.Engine
import com.example.demoapp.viewmodlelivedataflow.Wheel
import com.example.demoapp.viewmodlelivedataflow.api.NewsHelper
import com.example.demoapp.viewmodlelivedataflow.api.NewsServiceProvider
import com.example.demoapp.viewmodlelivedataflow.newviewmodel.MainViewModel
import com.example.demoapp.viewmodlelivedataflow.repository.NewsArticleRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object NewsModule {

    val dataModule = module {
        factory { Wheel() }
        factory { Engine() }
        factory { Car(get(),get()) }
    }


//    val newsService = NewsHelper.getInstance().create(NewsServiceProvider::class.java)
//    val repository = NewsArticleRepository(newsService)
//    mainViewModel = ViewModelProvider(
//    requireActivity(),
//    MainViewModelFactory(repository)
//    )[MainViewModel::class.java]

    val mainViewModule = module {
//        factory {
//
//        }
        single<NewsServiceProvider>{
            NewsHelper.getInstance().create(NewsServiceProvider::class.java)
        }

        single<NewsArticleRepository> {
            NewsArticleRepository(get())
        }
        viewModel {
//             ViewModelProvider()

            MainViewModel(get()) }


    }

}