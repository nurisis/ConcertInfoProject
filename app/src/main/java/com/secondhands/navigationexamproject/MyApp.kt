package com.secondhands.navigationexamproject

import android.app.Application
import com.secondhands.navigationexamproject.di.appModule
import com.secondhands.navigationexamproject.di.netWorkModule
import com.secondhands.navigationexamproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(viewModelModule, netWorkModule, appModule))
        }
    }
}