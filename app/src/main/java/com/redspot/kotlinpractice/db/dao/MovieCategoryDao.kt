package com.redspot.kotlinpractice.db.dao

import androidx.room.*
import com.redspot.kotlinpractice.db.entity.CategoryMoviesCrossRef
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.db.entity.MovieCategory

@Dao
interface MovieCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCategory(entity: MovieCategory) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>) : Array<Long>

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE movieId = :id")
    fun getMovieById(id: Long) : Movie

    @Query("SELECT * FROM categories")
    fun getAllCategories() : List<MovieCategory>

    @Query("SELECT * FROM categories WHERE categoryName = :name")
    fun getCategoryByName(name: String) : MovieCategory

    @Transaction
    @Query("SELECT * FROM movies WHERE movieId IN (SELECT movieId FROM categories_movies WHERE categoryId IN (SELECT categoryId FROM categories WHERE categoryName = :categoryName))")
    fun getMoviesFromCategoryByName(categoryName: String) : List<Movie>

    @Transaction
    fun insertCategoryWithMovies(movies: List<Movie>, categoryName: String) {
        val movieIds = insertMovies(movies)
        val categoryId = insertMovieCategory(MovieCategory(name = categoryName))
        for (movieId in movieIds) {
            insertCategoryMoviesCrossRef(CategoryMoviesCrossRef(categoryId, movieId))
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryMoviesCrossRef(entity: CategoryMoviesCrossRef)

    @Delete
    fun deleteMovie(entity: Movie)

    @Delete
    fun deleteMovieCategory(entity: MovieCategory)
}