package com.redspot.kotlinpractice.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val movieId: Long,
    val title: String,
    val adult: Boolean,
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String
) : Parcelable
