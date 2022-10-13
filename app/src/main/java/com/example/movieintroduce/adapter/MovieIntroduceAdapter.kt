package com.example.movieintroduce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ItemMovieBinding
import com.example.movieintroduce.model.Movie

class MovieIntroduceAdapter(
    private val listener: (item: Movie) -> Unit
) : PagingDataAdapter<Movie, MovieIntroduceAdapter.MovieViewHolder>(
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
        val movieListItemBinding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_movie, parent, false
        )

        return MovieViewHolder(movieListItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { item ->
                    listener.invoke(item)
                }
            }
        }

        fun onBind(item: Movie?) {
            binding.movie = item
        }

    }
}