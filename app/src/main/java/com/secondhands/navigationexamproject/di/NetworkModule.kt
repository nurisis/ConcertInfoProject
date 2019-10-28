package com.secondhands.navigationexamproject.di

import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val netWorkModule = module {

    single {
        GsonBuilder().create()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://www.culture.go.kr")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

}