package com.redspot.kotlinpractice.framework.ui.details

import androidx.lifecycle.ViewModel
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.model.repository.Repository
import kotlinx.coroutines.*

class DetailsViewModel(private val repository: Repository) : ViewModel(),
    CoroutineScope by MainScope() {
    suspend fun writeMovieToDb(movie: Movie) {
        withContext(Dispatchers.Default) {
            repository.insertMovieInDatabase(movie)
        }
    }

    suspend fun getMovieByIdFromDb(id: Long) = withContext(Dispatchers.Default) {
        repository.getMovieById(id)
    }
}
