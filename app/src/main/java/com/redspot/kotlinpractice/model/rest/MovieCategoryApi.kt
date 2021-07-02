package com.redspot.kotlinpractice.model.rest

import com.redspot.kotlinpractice.BuildConfig
import com.redspot.kotlinpractice.model.rest.rest_entities.MoviesCategoryDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val CATEGORY_POPULAR = "popular"
const val CATEGORY_UPCOMING = "upcoming"
const val CATEGORY_TOP_RATED = "top_rated"

interface MovieCategoryApi {
    @GET("{category}")
    fun getCategory(
        @Path("category") category: String,
        @Query("language") language: String = "en-US",
        @Query("api_key") api: String = BuildConfig.TMDB_API_KEY
    ) : Call<MoviesCategoryDTO>
}