package com.redspot.kotlinpractice.db.dao

import androidx.room.*
import com.redspot.kotlinpractice.db.entity.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: Movie)

    @Query("SELECT * FROM movies")
    fun getAll(): ArrayList<Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Long)

    @Delete
    fun delete(entity: Movie)
}