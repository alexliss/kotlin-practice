package com.redspot.kotlinpractice.di

import androidx.room.Room
import com.redspot.kotlinpractice.db.MovieDatabase
import com.redspot.kotlinpractice.framework.ui.details.DetailsViewModel
import com.redspot.kotlinpractice.framework.ui.main.MainViewModel
import com.redspot.kotlinpractice.model.repository.Repository
import com.redspot.kotlinpractice.model.repository.RepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single(named("MovieCategoryDao")) {
        get<MovieDatabase>().movieCategoryDao()
    }

    single<Repository> { RepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}