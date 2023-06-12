package com.example.demoapp.koindemo

import android.app.Application
import com.example.demoapp.viewmodlelivedataflow.module.NewsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(NewsModule.dataModule, NewsModule.mainViewModule, NewsModule.retrofit,NewsModule.sharedModule)

        }

    }
}