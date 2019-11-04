package com.secondhands.navigationexamproject.di

import com.secondhands.navigationexamproject.ui.DetailViewModel
import com.secondhands.navigationexamproject.ui.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get(), get()) }
    viewModel { DetailViewModel() }
}