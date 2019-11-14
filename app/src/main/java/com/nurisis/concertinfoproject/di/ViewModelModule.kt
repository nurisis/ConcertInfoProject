package com.nurisis.concertinfoproject.di

import com.nurisis.concertinfoproject.ui.ConcertViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ConcertViewModel(get(),get(),get(),get()) }
}