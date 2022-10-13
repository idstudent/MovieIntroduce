package com.example.movieintroduce.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.presentation.adapter.MyLikeMovieAdapter
import com.example.movieintroduce.databinding.ActivityMyLikeBinding
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.listener.ItemClickListener
import com.example.movieintroduce.presentation.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyLikeActivity : AppCompatActivity() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    @Inject lateinit var adapter  : MyLikeMovieAdapter
    private lateinit var binding : ActivityMyLikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_like)

        getLikeMovies()
    }

    private fun getLikeMovies() {
        lifecycleScope.launch {
            movieDetailViewModel.getMovies().collect {
                showMyLikeMovies(it)
            }
        }
    }
    private fun showMyLikeMovies(nowMoviesResponse: List<Movie>) {
        val movieRecycler = binding.likeRecycler
        adapter = MyLikeMovieAdapter()
        adapter.differ.submitList(nowMoviesResponse)
        adapter.itemClickListener(listener)
        movieRecycler.layoutManager = GridLayoutManager(this@MyLikeActivity, 2)
        movieRecycler.adapter = adapter
    }

    val listener = object : ItemClickListener<Movie> {
        override fun onClick(item: Movie) {
            val intent = Intent(this@MyLikeActivity, MyLikeMovieDetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }
}