package com.example.demoapp.koindemo

import android.app.Application
import com.example.demoapp.viewmodlelivedataflow.module.NewsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application()  {
    override fun onCreate() {
        super.onCreate()

        startKoin {
             modules(NewsModule.dataModule,NewsModule.mainViewModule,NewsModule.retrofit)
            androidContext(this@MainApplication)
        }

    }
}