package com.redspot.kotlinpractice.model.repository

import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.entities.Movie

class RepositoryImpl : Repository {
    override fun getMovieFromServer() = Movie()

    override fun getMovieFromLocalStorage() = Movie()

    override fun getCategoriesFromLocalStorage() = getTestCategories(10)

    override fun getCategoriesFromServer() = getTestCategories(10)

    private fun getMoviesForCategory(count: Int) = mutableListOf<Movie>().apply {
        for (i in 0 until count) {
            add(Movie(12345, "Fight $i Club", true, "Some description. Maybe."))
        }
    }

    private fun getTestCategories(count: Int) = mutableListOf<MoviesCategory>().apply {
        val titles = listOf("Classics", "Top Ratings", "In Theaters", "Soon")

        for (i in 0 until count) {
            val title = titles[i % titles.size] + " " + i.toString()
            add(MoviesCategory(title, getMoviesForCategory(count)))
        }
    }
}