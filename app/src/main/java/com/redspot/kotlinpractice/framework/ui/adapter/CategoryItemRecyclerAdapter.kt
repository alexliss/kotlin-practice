package com.redspot.kotlinpractice.framework.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redspot.kotlinpractice.databinding.CategoryRecyclerRowItemBinding
import com.redspot.kotlinpractice.db.entity.Movie
import com.redspot.kotlinpractice.model.rest.imageUrl
import com.squareup.picasso.Picasso

class CategoryItemRecyclerAdapter(
    private val movies: List<Movie>,
    var movieInteraction: Interaction
) : RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemViewHolder {
        val binding = CategoryRecyclerRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    inner class CategoryItemViewHolder(
        private val _binding: CategoryRecyclerRowItemBinding
    ) : RecyclerView.ViewHolder(_binding.root) {

        fun bind(movie: Movie) = with(_binding) {
            movieTitle.text = movie.title
            Picasso
                .get()
                .load(imageUrl + movie.posterPath)
                .fit()
                .centerCrop()
                .into(moviePoster)
            movieCard.setOnClickListener {
                movieInteraction.onClickItem(movie)
            }
        }
    }

    interface Interaction {
        fun onClickItem(movie: Movie)
    }
}