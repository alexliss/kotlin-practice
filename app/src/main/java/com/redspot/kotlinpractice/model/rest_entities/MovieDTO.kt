package com.redspot.kotlinpractice.model.rest_entities

data class MovieDTO(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val overview: String,
    val voteAverage: Float,
    val releaseDate: String
)
