package com.redspot.kotlinpractice.framework.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redspot.kotlinpractice.databinding.CategoryRecyclerRowItemBinding
import com.redspot.kotlinpractice.model.entities.Movie

class CategoryItemRecyclerAdapter(
    private val movies: List<Movie>,
    var movieInteraction: Interaction
) : RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder>() {
    private lateinit var binding: CategoryRecyclerRowItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemViewHolder {
        binding = CategoryRecyclerRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    inner class CategoryItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) = with(binding) {
            movieTitle.text = movie.title
            movieCard.setOnClickListener {
                movieInteraction.onClickItem(movie)
            }
        }
    }

    interface Interaction {
        fun onClickItem(movie: Movie)
    }
}