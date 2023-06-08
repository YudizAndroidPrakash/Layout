package com.example.demoapp.viewmodlelivedataflow.module

import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.viewmodlelivedataflow.Car
import com.example.demoapp.viewmodlelivedataflow.Engine
import com.example.demoapp.viewmodlelivedataflow.Wheel
import com.example.demoapp.viewmodlelivedataflow.api.NewsHelper
import com.example.demoapp.viewmodlelivedataflow.api.NewsServiceProvider
import com.example.demoapp.viewmodlelivedataflow.newviewmodel.MainViewModel
import com.example.demoapp.viewmodlelivedataflow.newviewmodel.NewsViewModel
import com.example.demoapp.viewmodlelivedataflow.repository.NewsArticleRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsModule {

    val dataModule = module {
        factory { Wheel() }
        factory { Engine() }
        factory { Car(get(), get()) }
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
        single<NewsServiceProvider> {
            NewsHelper.getInstance().create(NewsServiceProvider::class.java)
        }

        single<NewsArticleRepository> {
            NewsArticleRepository(get())
        }
        viewModel {
//             ViewModelProvider()

            MainViewModel(get())
        }
    }


    private const val BASE_URL = "https://newsapi.org/v2/"
    private fun apiServiceProvider(): NewsServiceProvider =

        Retrofit.Builder().run {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            build()
        }.create(NewsServiceProvider::class.java)


    val retrofit = module {
        single {
            apiServiceProvider()
        }
//        single<NewsServiceProvider> {
//            NewsHelper.getInstance().create(get())
//        }

        single {
            NewsArticleRepository(get())
        }
        viewModel {
//             ViewModelProvider()

            NewsViewModel(get())
        }

    }
}




