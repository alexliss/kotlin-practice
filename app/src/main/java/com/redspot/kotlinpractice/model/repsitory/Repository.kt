package com.redspot.kotlinpractice.model.repsitory

import com.redspot.kotlinpractice.model.AllCategory
import com.redspot.kotlinpractice.model.entities.Movie

interface Repository {
    fun getMovieFromServer() : Movie
    fun getMovieFromLocalStorage() : Movie
    fun getCategoriesFromLocalStorage() : List<AllCategory>
    fun getCategoriesFromServer() : List<AllCategory>
}