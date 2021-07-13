package com.redspot.kotlinpractice.model.repository

import android.content.SharedPreferences
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.model.entities.MoviesCategory

interface Repository {
    fun getCategoriesFromServer(sharedPreferences: SharedPreferences) : List<MoviesCategory>

    fun getCategoriesFromLocalSource(sharedPreferences: SharedPreferences) : List<MoviesCategory>

    fun getMovieById(id: Long) : Movie

    fun insertCategoriesInDatabase(categories: List<MoviesCategory>)

    fun insertMovieInDatabase(movie: Movie)
}