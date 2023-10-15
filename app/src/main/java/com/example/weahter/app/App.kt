package com.example.weahter.app

import android.app.Application
import com.example.weahter.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(dataModule)
        }
    }


}