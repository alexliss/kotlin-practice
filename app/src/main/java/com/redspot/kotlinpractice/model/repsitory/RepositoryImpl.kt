package com.redspot.kotlinpractice.model.repsitory

import com.redspot.kotlinpractice.model.AllCategory
import com.redspot.kotlinpractice.model.entities.Movie

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): Movie {
        return Movie()
    }

    override fun getMovieFromLocalStorage(): Movie {
        return Movie()
    }

    override fun getCategoriesFromLocalStorage(): List<AllCategory> {
        return getTestCategories()
    }

    override fun getCategoriesFromServer(): List<AllCategory> {
        return getTestCategories()
    }

    private fun getTestCategories() : List<AllCategory> {
        val testMovieList = mutableListOf<Movie>()
        for(i in 1..10) {
            testMovieList.add(Movie())
        }
        val testCategoryList: MutableList<AllCategory> = mutableListOf()
        testCategoryList.add(AllCategory("Classics", testMovieList))
        testCategoryList.add(AllCategory("Top Ratings", testMovieList))
        testCategoryList.add(AllCategory("In Theaters", testMovieList))
        testCategoryList.add(AllCategory("Soon", testMovieList))
        return testCategoryList
    }
}