package com.redspot.kotlinpractice.model.repository

import com.redspot.kotlinpractice.model.CATEGORY_POPULAR
import com.redspot.kotlinpractice.model.CATEGORY_TOP_RATED
import com.redspot.kotlinpractice.model.CATEGORY_UPCOMING
import com.redspot.kotlinpractice.model.DataLoader
import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.entities.Movie
import com.redspot.kotlinpractice.model.rest.MovieCategoryRepo
import com.redspot.kotlinpractice.model.rest.rest_entities.MovieDTO

class RepositoryImpl : Repository {
    override fun getMovieFromServer() = Movie()

    override fun getMovieFromLocalStorage() = Movie()

    override fun getCategoriesFromLocalStorage() = getTestCategories(10)

    override fun getCategoriesFromServer() = mutableListOf<MoviesCategory>().apply {
        add(getPopularCategory())
        add(getTopRatedCategory())
        add(getUpcomingCategory())
    }

    private fun convertMovieGTOToMovie(movieDTO : MovieDTO) = Movie(
        id = movieDTO.id,
        title = movieDTO.title,
        adult = movieDTO.adult,
        overview = movieDTO.overview,
        voteAverage = movieDTO.vote_average,
        releaseDate = movieDTO.release_date
    )

    private fun getPopularCategory() : MoviesCategory {
        //val dto = DataLoader.loadCategory(CATEGORY_POPULAR)
        val dto = MovieCategoryRepo.api.getCategory(CATEGORY_POPULAR).execute().body()
        if (dto?.results == null) throw Exception("NULL")
        return MoviesCategory("Popular", dto.results)
    }

    private fun getTopRatedCategory() : MoviesCategory {
        val  dto = MovieCategoryRepo.api.getCategory(CATEGORY_TOP_RATED).execute().body()
        if (dto?.results == null) throw Exception("NULL")
        return MoviesCategory("Top Rated", dto.results)

    }

    private fun getUpcomingCategory() : MoviesCategory {
        val dto = MovieCategoryRepo.api.getCategory(CATEGORY_UPCOMING).execute().body()
        if (dto?.results == null) throw Exception("NULL")
        return MoviesCategory("Upcoming", dto.results)
    }

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