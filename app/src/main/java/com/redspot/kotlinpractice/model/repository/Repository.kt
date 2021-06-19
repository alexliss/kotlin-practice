package com.redspot.kotlinpractice.model.repository

import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.entities.Movie

interface Repository {
    fun getMovieFromServer() : Movie
    fun getMovieFromLocalStorage() : Movie
    fun getCategoriesFromLocalStorage() : List<MoviesCategory>
    fun getCategoriesFromServer() : List<MoviesCategory>
}