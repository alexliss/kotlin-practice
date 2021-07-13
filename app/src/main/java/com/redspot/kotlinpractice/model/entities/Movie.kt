package com.redspot.kotlinpractice.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long = 17473,
    val title: String = "The Room",
    val adult: Boolean = true,
    val overview: String = "Johnny is a successful banker with great respect for and dedication to the people in his life, especially his future wife Lisa. The happy-go-lucky guy sees his world being torn apart when his friends begin to betray him one-by-one.",
    @SerializedName("vote_average")
    val voteAverage: Float = 4.1F,
    @SerializedName("release_date")
    val releaseDate: String = "27/06/2003",
    @SerializedName("poster_path")
    val posterPath: String = "haha"
    //val tagline: String = "Can you ever really trust anyone?"
) : Parcelable