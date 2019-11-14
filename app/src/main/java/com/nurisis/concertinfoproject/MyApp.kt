package com.nurisis.concertinfoproject

import android.app.Application
import com.nurisis.concertinfoproject.di.appModule
import com.nurisis.concertinfoproject.di.netWorkModule
import com.nurisis.concertinfoproject.di.viewModelModule
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