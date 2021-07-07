package com.redspot.kotlinpractice.model.repository

import com.redspot.kotlinpractice.model.entities.MoviesCategory

interface Repository {
    fun getCategoriesFromServer() : List<MoviesCategory>

    fun getCategoriesFromLocalSource() : List<MoviesCategory>

    fun insertCategoriesInDatabase(categories: List<MoviesCategory>)
}