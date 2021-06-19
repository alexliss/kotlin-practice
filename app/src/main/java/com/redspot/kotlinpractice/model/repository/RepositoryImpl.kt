package com.redspot.kotlinpractice.model.repository

import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.entities.Movie

class RepositoryImpl : Repository {
    override fun getMovieFromServer() = Movie()

    override fun getMovieFromLocalStorage() = Movie()

    override fun getCategoriesFromLocalStorage() = getTestCategories()

    override fun getCategoriesFromServer() = getTestCategories()

    private fun getTestCategory() = mutableListOf<Movie>().apply {
        for (i in 1..5) {
            add(Movie())
            add(Movie(12345, "Fight Club", true, "Some description. Maybe."))
        }
    }

    private fun getTestCategories() = mutableListOf<MoviesCategory>().apply {
        add(MoviesCategory("Classics", getTestCategory()))
        add(MoviesCategory("Top Ratings", getTestCategory()))
        add(MoviesCategory("In Theaters", getTestCategory()))
        add(MoviesCategory("Soon", getTestCategory()))
    }
}