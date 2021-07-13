package com.redspot.kotlinpractice.framework.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.redspot.kotlinpractice.databinding.DetailsFragmentBinding
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.model.rest.imageUrl
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding
    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var movie: Movie

    // создание фрагмента
    companion object {
        private const val ARG = "MovieID"

        fun newInstance(movieId: Long) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG, movieId)
            }
        }
    }

    // привязываем binding, все дела
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    // основные дела
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong(ARG)?.let {
            runBlocking {
                movie = viewModel.getMovieByIdFromDb(it)
            }
        }
        setData()
        Log.d("MOVIE", "${movie.watched}")
        binding.watched.setOnCheckedChangeListener { _, isChecked ->
            movie.watched = isChecked
            Log.d("MOVIE", "${movie.movieId} ${movie.popularity} ${movie.watched}")
            runBlocking {
                viewModel.writeMovieToDb(movie)
            }
        }
    }

    // ля какава красота
    private fun setData() = with(binding) {
        movieTitle.text = movie.title
        ratingValue.text = movie.voteAverage.toString()
        releaseDate.text = movie.releaseDate
        movieOverview.text = movie.overview
        watched.isChecked = movie.watched
        userRatingBar.rating = movie.userVote.toFloat() / 2
        userRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            movie.userVote = (rating * 2).toInt()
            runBlocking {
                viewModel.writeMovieToDb(movie)
            }
        }
        Picasso
            .get()
            .load(imageUrl + movie.posterPath)
            .fit()
            .centerCrop()
            .into(poster)
    }
}