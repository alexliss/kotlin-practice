package com.redspot.kotlinpractice.framework.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redspot.kotlinpractice.databinding.MainRecyclerRowItemBinding
import com.redspot.kotlinpractice.model.entities.MoviesCategory
import com.redspot.kotlinpractice.model.entities.Movie

class MainRecyclerAdapter(
    private val context: Context?,
    private var moviesCategoryList: List<MoviesCategory>,
    private var movieInteraction: CategoryItemRecyclerAdapter.Interaction
    ) : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    private lateinit var binding: MainRecyclerRowItemBinding
    private val moviesAdaptersList = mutableListOf<CategoryItemRecyclerAdapter>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainRecyclerAdapter.MainViewHolder {
        binding = MainRecyclerRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        binding.moviesRecycler.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
        createCategoryItemRecyclerAdapters()
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainRecyclerAdapter.MainViewHolder, position: Int) {
        holder.bind(moviesCategoryList[position])
        binding.moviesRecycler.adapter = moviesAdaptersList[position]
    }

    override fun getItemCount() = moviesCategoryList.size

    private fun createCategoryItemRecyclerAdapters() {
        moviesAdaptersList.apply {
            for (moviesList in moviesCategoryList) {
                add(CategoryItemRecyclerAdapter(moviesList.movies, movieInteraction))
            }
        }
    }

    inner class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(moviesCategory: MoviesCategory) = with(binding) {
            catTitle.text = moviesCategory.categoryTitle
        }
    }
}