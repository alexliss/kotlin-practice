package com.redspot.kotlinpractice.model.entities

import com.redspot.kotlinpractice.db.entity.Movie

class MoviesCategory(var categoryTitle: String, val movies: List<Movie>)