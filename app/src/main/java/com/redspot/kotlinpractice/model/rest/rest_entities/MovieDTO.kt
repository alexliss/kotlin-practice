package com.redspot.kotlinpractice.model.rest.rest_entities

data class MovieDTO(
    val id: Int = 17473,
    val title: String = "The Room",
    val adult: Boolean = true,
    val overview: String = "Johnny is a successful banker with great respect for and dedication to the people in his life, especially his future wife Lisa. The happy-go-lucky guy sees his world being torn apart when his friends begin to betray him one-by-one.",
    val vote_average: Float = 4.1F,
    val release_date: String = "27/06/2003"
)