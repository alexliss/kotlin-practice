package com.redspot.kotlinpractice.di

import androidx.room.Room
import com.redspot.kotlinpractice.db.Database
import com.redspot.kotlinpractice.framework.ui.main.MainViewModel
import com.redspot.kotlinpractice.model.repository.Repository
import com.redspot.kotlinpractice.model.repository.RepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            Database::class.java,
            Database.DATABASE_NAME
        ).build()
    }

    single(named("MovieDao")) {
        get<Database>().movieDao()
    }

    single(named("MovieCategoryDao")) {
        get<Database>().movieCategoryDao()
    }
}