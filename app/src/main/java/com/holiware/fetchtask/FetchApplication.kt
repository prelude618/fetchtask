package com.holiware.fetchtask

import android.app.Application
import com.holiware.fetchtask.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FetchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }
    }
}