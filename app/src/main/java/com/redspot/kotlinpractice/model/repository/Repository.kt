package com.redspot.kotlinpractice.model.repository

import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.entities.Movie

interface Repository {
    fun getCategoriesFromServer() : List<MoviesCategory>
}