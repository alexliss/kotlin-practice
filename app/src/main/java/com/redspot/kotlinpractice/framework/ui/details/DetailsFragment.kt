package com.redspot.kotlinpractice.framework.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.redspot.kotlinpractice.databinding.DetailsFragmentBinding
import com.redspot.kotlinpractice.model.entities.Movie
import com.redspot.kotlinpractice.model.rest.imageUrl
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding

    // создание фрагмента
    companion object {
        private const val ARG = "Movie"

        fun newInstance(movie: Movie) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG, movie)
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
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    // ля какава красота
    private fun setData() = with(binding) {
        arguments?.getParcelable<Movie>(ARG)?.let { movie ->
            movieTitle.text = movie.title
            ratingValue.text = movie.voteAverage.toString()
            //tagline.text = movie.tagline
            releaseDate.text = movie.releaseDate
            movieOverview.text = movie.overview
            Picasso
                .get()
                .load(imageUrl + movie.posterPath)
                .fit()
                .centerCrop()
                .into(poster)

            if (movie.adult) {
                adult.visibility = View.VISIBLE
                Log.d("LOSHARA", "lohhh")
            }
        }
    }
}