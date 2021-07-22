package com.example.movieintroduce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ItemLikeMovieBinding
import com.example.movieintroduce.databinding.ItemMovieBinding
import com.example.movieintroduce.db.Movie
import com.example.movieintroduce.listener.ItemClickListener

class MyLikeMovieAdapter(
        private val context : Context,
        private val listItems : List<Movie>
) : RecyclerView.Adapter<MyLikeMovieAdapter.MovieViewHolder>() {

    private lateinit var listener : ItemClickListener<Movie>

    fun itemClickListener(listener: ItemClickListener<Movie>) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieListItemBinding : ItemLikeMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(context)
                , R.layout.item_like_movie, parent, false)

        return MovieViewHolder(movieListItemBinding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)  {
        holder.movieListItemBinding.movie = listItems[position]

        holder.movieListItemBinding.root.setOnClickListener {
            listener.onClick(listItems[position])
        }
    }
    inner class MovieViewHolder(itemMovieBinding: ItemLikeMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        var movieListItemBinding : ItemLikeMovieBinding = itemMovieBinding
    }

}