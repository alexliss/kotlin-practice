package com.redspot.kotlinpractice.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.redspot.kotlinpractice.db.dao.MovieCategoryDao
import com.redspot.kotlinpractice.db.dao.MovieDao
import com.redspot.kotlinpractice.db.entity.CategoryMoviesCrossRef
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.db.entity.MovieCategory

@Database(
    entities = [Movie::class, MovieCategory::class, CategoryMoviesCrossRef::class],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun movieCategoryDao(): MovieCategoryDao

    companion object {
        const val DATABASE_NAME = "movies_database"
    }
}