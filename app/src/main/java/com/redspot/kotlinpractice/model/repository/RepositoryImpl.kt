package com.redspot.kotlinpractice.model.repository

import com.redspot.kotlinpractice.db.MovieDatabase
import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.rest.CATEGORY_POPULAR
import com.redspot.kotlinpractice.model.rest.CATEGORY_TOP_RATED
import com.redspot.kotlinpractice.model.rest.CATEGORY_UPCOMING
import com.redspot.kotlinpractice.model.rest.MovieCategoryRepo

class RepositoryImpl(val database: MovieDatabase) : Repository {

    override fun getCategoriesFromServer() = mutableListOf<MoviesCategory>().apply {
        add(getPopularCategory())
        add(getTopRatedCategory())
        add(getUpcomingCategory())
    }.also { categories ->
        insertCategoriesInDatabase(categories)
    }

    override fun getCategoriesFromLocalSource() = mutableListOf<MoviesCategory>().apply {
        add(
            MoviesCategory(
                "Popular",
                database.movieCategoryDao().getMoviesFromCategoryByName("Popular")
            )
        )
        add(
            MoviesCategory(
                "Top Rated",
                database.movieCategoryDao().getMoviesFromCategoryByName("Top Rated")
            )
        )
        add(
            MoviesCategory(
                "Upcoming",
                database.movieCategoryDao().getMoviesFromCategoryByName("Upcoming")
            )
        )
    }

    override fun insertCategoriesInDatabase(categories: List<MoviesCategory>) {
        for (category in categories) {
            database.movieCategoryDao()
                .insertCategoryWithMovies(category.movies, category.categoryTitle)
        }
    }

    private fun getPopularCategory(): MoviesCategory {
        //val dto = DataLoader.loadCategory(CATEGORY_POPULAR)
        val dto = MovieCategoryRepo.api.getCategory(CATEGORY_POPULAR).execute().body()
        if (dto?.results == null) throw Exception("NULL")
        return MoviesCategory("Popular", dto.results)
    }

    private fun getTopRatedCategory(): MoviesCategory {
        val dto = MovieCategoryRepo.api.getCategory(CATEGORY_TOP_RATED).execute().body()
        if (dto?.results == null) throw Exception("NULL")
        return MoviesCategory("Top Rated", dto.results)

    }

    private fun getUpcomingCategory(): MoviesCategory {
        val dto = MovieCategoryRepo.api.getCategory(CATEGORY_UPCOMING).execute().body()
        if (dto?.results == null) throw Exception("NULL")
        return MoviesCategory("Upcoming", dto.results)
    }
}