package com.example.movieintroduce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ItemLikeMovieBinding
import com.example.movieintroduce.model.Movie

class MyLikeMovieAdapter(
    private val listener: (item: Movie) -> Unit
) : ListAdapter<Movie, MyLikeMovieAdapter.MovieViewHolder>(
    object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieListItemBinding: ItemLikeMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_like_movie, parent, false
        )

        return MovieViewHolder(movieListItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class MovieViewHolder(
        private val binding: ItemLikeMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                binding.movie?.let {
                    listener.invoke(it)
                }
            }
        }
        fun onBind(movie: Movie) {
            binding.movie = movie
        }
    }
}