package com.redspot.kotlinpractice.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithMovies(
    @Embedded
    val category: MovieCategory,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "movieId",
        associateBy = Junction(CategoryMoviesCrossRef::class)
    )
    val movies: ArrayList<Movie>
)
