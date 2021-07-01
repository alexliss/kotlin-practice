package com.redspot.kotlinpractice

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.redspot.kotlinpractice.model.CATEGORY_POPULAR
import com.redspot.kotlinpractice.model.CATEGORY_TOP_RATED
import com.redspot.kotlinpractice.model.CATEGORY_UPCOMING
import com.redspot.kotlinpractice.model.DataLoader
import com.redspot.kotlinpractice.model.entities.Movie
import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.repository.RepositoryImpl
import com.redspot.kotlinpractice.model.rest_entities.MovieDTO

class LoadingService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        sendMyBroadcast(getCategoriesFromServer())
    }

    fun getCategoriesFromServer() = arrayListOf<MoviesCategory>().apply {
        add(getPopularCategory())
        add(getTopRatedCategory())
        add(getUpcomingCategory())
    }

    private fun convertMovieGTOToMovie(movieDTO : MovieDTO) = Movie(
        id = movieDTO.id,
        title = movieDTO.title,
        adult = movieDTO.adult,
        overview = movieDTO.overview,
        voteAverage = movieDTO.vote_average,
        releaseDate = movieDTO.release_date
    )

    private fun sendMyBroadcast(movies: ArrayList<MoviesCategory>) {
        val broadcastIntent = Intent()
        broadcastIntent.putExtra(INTENT_SERVICE_DATA, movies)
        broadcastIntent.action = INTENT_ACTION_KEY
        sendBroadcast(broadcastIntent)
    }

    private fun getPopularCategory() : MoviesCategory {
        val dto = DataLoader.loadCategory(CATEGORY_POPULAR)
        val converted = mutableListOf<Movie>()
        dto?.let {
            for (movie in it.results) {
                converted.add(convertMovieGTOToMovie(movie))
            }
        }
        return MoviesCategory("Popular", converted)
    }

    private fun getTopRatedCategory() : MoviesCategory {
        val dto = DataLoader.loadCategory(CATEGORY_TOP_RATED)
        val converted = mutableListOf<Movie>()
        dto?.let {
            for (movie in it.results) {
                converted.add(convertMovieGTOToMovie(movie))
            }
        }
        return MoviesCategory("Popular", converted)
    }

    private fun getUpcomingCategory() : MoviesCategory {
        val dto = DataLoader.loadCategory(CATEGORY_UPCOMING)
        val converted = mutableListOf<Movie>()
        dto?.let {
            for (movie in it.results) {
                converted.add(convertMovieGTOToMovie(movie))
            }
        }
        return MoviesCategory("Upcoming", converted)
    }

    companion object {
        const val INTENT_ACTION_KEY = "com.redspot.kotlinpractice.DATA_LOADED"
        const val INTENT_SERVICE_DATA = "INTENT_SERVICE_DATA"

        fun start(context: Context) {
            val intent = Intent(context, LoadingService::class.java)
            enqueueWork(context, LoadingService::class.java, 3322, intent)
        }
    }
}