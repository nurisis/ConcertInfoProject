package com.secondhands.concertinfoproject.di

import com.secondhands.concertinfoproject.data.ConcertRepository
import com.secondhands.concertinfoproject.data.local.ConcertLocalDataSource
import com.secondhands.concertinfoproject.data.local.Database
import com.secondhands.concertinfoproject.data.remote.ConcertRemoteDataSource
import com.secondhands.concertinfoproject.domain.DeleteBookMarkUseCase
import com.secondhands.concertinfoproject.domain.GetBookMarkUseCase
import com.secondhands.concertinfoproject.domain.GetConcertsUseCase
import com.secondhands.concertinfoproject.domain.SaveBookMarkUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { SaveBookMarkUseCase(get()) }
    single { DeleteBookMarkUseCase(get()) }
    single { GetBookMarkUseCase(get()) }
    single { GetConcertsUseCase(get()) }

    single { ConcertRemoteDataSource(get()) }
    single { ConcertRepository(get(),get()) }
    single { ConcertLocalDataSource(Database.getDatabase(androidContext()).concertDao()) }
}