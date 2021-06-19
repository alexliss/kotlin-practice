package com.redspot.kotlinpractice.di

import com.redspot.kotlinpractice.framework.ui.main.MainViewModel
import com.redspot.kotlinpractice.model.repository.Repository
import com.redspot.kotlinpractice.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
}