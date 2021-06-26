package com.redspot.kotlinpractice.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redspot.kotlinpractice.databinding.MainRecyclerRowItemBinding
import com.redspot.kotlinpractice.model.AllCategory
import com.redspot.kotlinpractice.model.entities.Movie

class MainRecyclerAdapter(
    private val context: Context?,
    private var allCategoryList: List<AllCategory>,
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
        holder.bind(allCategoryList[position])
        setCatItemRecycler(binding.moviesRecycler, allCategoryList[position].movies)
    }

    override fun getItemCount(): Int {
        return allCategoryList.size
    }

    inner class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(allCategory: AllCategory) = with(binding) {
            catTitle.text = allCategory.categoryTitle
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