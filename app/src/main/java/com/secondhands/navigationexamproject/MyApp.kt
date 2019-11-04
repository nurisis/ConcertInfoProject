package com.secondhands.navigationexamproject

import android.app.Application
import com.secondhands.navigationexamproject.di.appModule
import com.secondhands.navigationexamproject.di.netWorkModule
import com.secondhands.navigationexamproject.di.viewModelModule
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            this@MyApp
            modules(listOf(viewModelModule, netWorkModule, appModule))
        }
    }
}