package com.secondhands.navigationexamproject.di

import com.google.gson.GsonBuilder
import com.secondhands.navigationexamproject.data.ConcertDatasource
import com.secondhands.navigationexamproject.data.ConcertRepository
import com.secondhands.navigationexamproject.data.ConcertRepositoryImpl
import com.secondhands.navigationexamproject.domain.GetConcertsUseCase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val netWorkModule = module {
    single { createOkHttp() }

    single {
        createWebService<ConcertDatasource>(get(), "http://www.culture.go.kr")
    }

    single {
        ConcertRepositoryImpl(get()) as ConcertRepository
    }

    single { GetConcertsUseCase(get()) }


//    single {
//        GsonBuilder().create()
//    }
//
//    single {
//        Retrofit.Builder()
//            .baseUrl("http://www.culture.go.kr")
//            .addConverterFactory(GsonConverterFactory.create(get()))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(get())
//            .build()
//    }

}


fun createOkHttp() : OkHttpClient {
//    val httpLoggingInterceptor = HttpLoggingInterceptor()
//    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
//        .addInterceptor(httpLoggingInterceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String) : T{

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(SimpleXmlConverterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}