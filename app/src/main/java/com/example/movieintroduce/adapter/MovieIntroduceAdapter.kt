package com.example.movieintroduce.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ItemMovieBinding
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.listener.ItemClickListener

class MovieIntroduceAdapter
    : PagingDataAdapter<Movie, MovieIntroduceAdapter.MovieViewHolder>(callback) {

    private lateinit var listener : ItemClickListener<Movie>

    fun itemClickListener(listener: ItemClickListener<Movie>) {
        this.listener = listener
    }

    companion object {
        private val callback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieListItemBinding : ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                , R.layout.item_movie, parent, false)

        return MovieViewHolder(movieListItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)  {
        Log.e("ljy", "리스트? ${ getItem(position)}")
        holder.movieListItemBinding.movie = getItem(position)

        holder.movieListItemBinding.root.setOnClickListener {
            getItem(position)?.let { it1 -> listener.onClick(it1) }
        }
    }
    inner class MovieViewHolder(itemMovieBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        var movieListItemBinding : ItemMovieBinding = itemMovieBinding
    }

}