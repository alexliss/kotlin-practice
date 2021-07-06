package com.redspot.kotlinpractice.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.db.entity.MovieCategory

interface MovieCategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: MovieCategory)

    @Query("SELECT * FROM categories")
    fun getAll() : ArrayList<MovieCategory>

    @Query("SELECT * FROM categories WHERE name = :name")
    fun getByName(name: String) : MovieCategory

    @Delete
    fun delete(entity: MovieCategory)
}