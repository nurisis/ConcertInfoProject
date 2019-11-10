package com.secondhands.concertinfoproject.di

import com.secondhands.concertinfoproject.ui.ConcertViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ConcertViewModel(get(),get(),get(),get()) }
}