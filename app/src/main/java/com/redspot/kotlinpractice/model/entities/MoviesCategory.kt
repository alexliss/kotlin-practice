package com.redspot.kotlinpractice.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MoviesCategory(var categoryTitle: String, val movies: List<Movie>) : Parcelable