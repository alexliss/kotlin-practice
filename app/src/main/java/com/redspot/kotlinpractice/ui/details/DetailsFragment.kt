package com.redspot.kotlinpractice.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.redspot.kotlinpractice.databinding.DetailsFragmentBinding
import com.redspot.kotlinpractice.model.AppState
import com.redspot.kotlinpractice.model.entities.Movie

class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

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

        // VM, слушатель для данных во VM и его привязка
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)

        // вытягиваем фильмец
        viewModel.getMovie()
    }

    // загрузка данных (ну типа)
    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                detailsLoading.visibility = View.GONE
                if (appState.data is Movie) {
                    setData(appState.data)
                }
            }
            is AppState.Loading -> {
                detailsLoading.visibility = View.VISIBLE
            }
            is AppState.Failure -> {
                detailsLoading.visibility = View.GONE
                Snackbar
                    .make(details, appState.msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getMovie() }
                    .show()
            }
        }
    }

    // ля какава красота
    private fun setData(movie: Movie) = with(binding) {
        movieTitle.text = movie.title
        ratingValue.text = movie.voteAverage.toString()
        tagline.text = movie.tagline
        releaseDate.text = movie.releaseDate
        movieOverview.text = movie.overview
        if (movie.adult) {
            adult.visibility = View.VISIBLE
        }
    }
}