package com.secondhands.concertinfoproject.di

import com.secondhands.concertinfoproject.data.remote.ConcertApiSource
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val netWorkModule = module {
    single { createOkHttp() }

    single {
        createWebService<ConcertApiSource>(get(), "http://www.culture.go.kr")
    }

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

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        // For xml parser
        .addConverterFactory(SimpleXmlConverterFactory.create())
        // For json parser
//        .addConverterFactory(GsonConverterFactory.create(gson))
        // For string parser
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}