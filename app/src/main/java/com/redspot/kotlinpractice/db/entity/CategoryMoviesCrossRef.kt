package com.redspot.kotlinpractice.db.entity

import androidx.room.Entity

@Entity(tableName = "categories_movies", primaryKeys = ["categoryId", "movieId"])
data class CategoryMoviesCrossRef(
    val categoryId: Long,
    val movieId: Long
)
