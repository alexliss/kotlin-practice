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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainRecyclerAdapter.MainViewHolder {
        binding = MainRecyclerRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainRecyclerAdapter.MainViewHolder, position: Int) {
        holder.bind(moviesCategoryList[position])
        setCatItemRecycler(binding.moviesRecycler, moviesCategoryList[position].movies)
    }

    override fun getItemCount() = moviesCategoryList.size

    inner class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(moviesCategory: MoviesCategory) = with(binding) {
            catTitle.text = moviesCategory.categoryTitle
        }
    }

    private fun setCatItemRecycler(
        recyclerView: RecyclerView,
        movies: List<Movie>
    ) {
        val itemRecyclerAdapter = CategoryItemRecyclerAdapter(movies, movieInteraction)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }
}