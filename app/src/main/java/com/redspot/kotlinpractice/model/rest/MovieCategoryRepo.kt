package com.redspot.kotlinpractice.model.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieCategoryRepo {
    val api: MovieCategoryApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHttpBuilder())
            .build()

        adapter.create(MovieCategoryApi::class.java)
    }
}