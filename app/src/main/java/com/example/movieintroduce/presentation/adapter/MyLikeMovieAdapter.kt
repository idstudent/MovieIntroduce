package com.example.movieintroduce.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ItemLikeMovieBinding
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.listener.ItemClickListener

class MyLikeMovieAdapter() : RecyclerView.Adapter<MyLikeMovieAdapter.MovieViewHolder>() {

    private lateinit var listener : ItemClickListener<Movie>

    private val callback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callback)

    fun itemClickListener(listener: ItemClickListener<Movie>) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieListItemBinding : ItemLikeMovieBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_like_movie, parent, false)

        return MovieViewHolder(movieListItemBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)  {
        holder.movieListItemBinding.movie =  differ.currentList[position]

        holder.movieListItemBinding.root.setOnClickListener {
            listener.onClick(differ.currentList[position])
        }
    }
    inner class MovieViewHolder(itemMovieBinding: ItemLikeMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        var movieListItemBinding : ItemLikeMovieBinding = itemMovieBinding
    }

}