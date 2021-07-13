package com.redspot.kotlinpractice.model.repository

import android.content.SharedPreferences
import com.redspot.kotlinpractice.db.MovieDatabase
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.rest.CATEGORY_POPULAR
import com.redspot.kotlinpractice.model.rest.CATEGORY_TOP_RATED
import com.redspot.kotlinpractice.model.rest.CATEGORY_UPCOMING
import com.redspot.kotlinpractice.model.rest.MovieCategoryRepo

class RepositoryImpl(private val database: MovieDatabase) : Repository {

    override fun getCategoriesFromServer(sharedPreferences: SharedPreferences) : List<MoviesCategory> {
        mutableListOf<MoviesCategory>().apply {
            add(getPopularCategory())
            add(getTopRatedCategory())
            add(getUpcomingCategory())
        }.also { categories ->
            insertCategoriesInDatabase(categories)
        }
        return getCategoriesFromLocalSource(sharedPreferences)
    }

    override fun getCategoriesFromLocalSource(sharedPreferences: SharedPreferences) = mutableListOf<MoviesCategory>().apply {
        if (sharedPreferences.getBoolean("show watched", false)) {
            add(
                MoviesCategory(
                    "Popular",
                    database.movieCategoryDao().getMoviesFromCategoryByName("Popular")
                )
            )
            add(
                MoviesCategory(
                    "Top Rated",
                    database.movieCategoryDao()
                        .getMoviesFromCategoryByNameSortByVoteAverage("Top Rated")
                )
            )
            add(
                MoviesCategory(
                    "Upcoming",
                    database.movieCategoryDao().getMoviesFromCategoryByName("Upcoming")
                )
            )
        } else {
            add(
                MoviesCategory(
                    "Popular",
                    database.movieCategoryDao().getMoviesFromCategoryByNameNotWatched("Popular")
                )
            )
            add(
                MoviesCategory(
                    "Top Rated",
                    database.movieCategoryDao()
                        .getMoviesFromCategoryByNameSortByVoteAverageNotWatched("Top Rated")
                )
            )
            add(
                MoviesCategory(
                    "Upcoming",
                    database.movieCategoryDao().getMoviesFromCategoryByNameNotWatched("Upcoming")
                )
            )
        }
    }

    override fun getMovieById(id: Long) = database.movieCategoryDao().getMovieById(id)

    override fun insertCategoriesInDatabase(categories: List<MoviesCategory>) {
        for (category in categories) {
            for (apiMovie in category.movies) {
                val dbMovie = database.movieCategoryDao().getMovieByIdNullable(apiMovie.movieId)
                if (dbMovie != null) {
                    apiMovie.watched = dbMovie.watched
                    apiMovie.userVote = dbMovie.userVote
                }
            }
            database.movieCategoryDao()
                .insertCategoryWithMovies(category.movies, category.categoryTitle)
        }
    }

    override fun insertMovieInDatabase(movie: Movie) {
        database.movieCategoryDao().insertMovie(movie)
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