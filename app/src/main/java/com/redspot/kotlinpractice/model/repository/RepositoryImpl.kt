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

    override fun getCategoriesFromServer() = mutableListOf<MoviesCategory>().apply {
        add(getPopularCategory())
        add(getTopRatedCategory())
        add(getUpcomingCategory())
    }

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
}