package com.redspot.kotlinpractice.model.rest

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

const val imageUrl = "https://image.tmdb.org/t/p/original"

object ApiUtils {
    private const val baseUrl = "https://api.themoviedb.org/"
    private const val version = "3/"
    private const val mediaType = "movie/"
    val url = "$baseUrl$version$mediaType"

    fun getOkHttpBuilder() : OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}