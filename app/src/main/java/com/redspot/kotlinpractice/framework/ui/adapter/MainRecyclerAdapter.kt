package com.redspot.kotlinpractice.framework.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redspot.kotlinpractice.databinding.MainRecyclerRowItemBinding
import com.redspot.kotlinpractice.model.entities.MoviesCategory

class MainRecyclerAdapter(
    private val context: Context?,
    private var moviesCategoryList: List<MoviesCategory>,
    private var movieInteraction: CategoryItemRecyclerAdapter.Interaction
    ) : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    private val moviesAdaptersList = mutableListOf<CategoryItemRecyclerAdapter>()

    init {
        moviesAdaptersList.apply {
            for (moviesList in moviesCategoryList) {
                add(CategoryItemRecyclerAdapter(moviesList.movies, movieInteraction))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainRecyclerAdapter.MainViewHolder {
        val binding = MainRecyclerRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        binding.moviesRecycler.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainRecyclerAdapter.MainViewHolder, position: Int) {
        holder.bind(moviesCategoryList[position], moviesAdaptersList[position])
    }

    override fun getItemCount() = moviesCategoryList.size

    inner class MainViewHolder(
        private val _binding: MainRecyclerRowItemBinding
    ) : RecyclerView.ViewHolder(_binding.root) {

        fun bind(moviesCategory: MoviesCategory, adapter: CategoryItemRecyclerAdapter) = with(_binding) {
            catTitle.text = moviesCategory.categoryTitle
            moviesRecycler.adapter = adapter
        }
    }
}