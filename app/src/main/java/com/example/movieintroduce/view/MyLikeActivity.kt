package com.example.movieintroduce.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MyLikeMovieAdapter
import com.example.movieintroduce.databinding.ActivityMyLikeBinding
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyLikeActivity : AppCompatActivity() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private val adapter : MyLikeMovieAdapter by lazy {
        MyLikeMovieAdapter { item ->
            val intent = Intent(this@MyLikeActivity, MovieDetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }

    private lateinit var binding : ActivityMyLikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_like)

        binding.run {
            likeRecycler.layoutManager = GridLayoutManager(this@MyLikeActivity, 2)
            likeRecycler.adapter = adapter
        }

        lifecycleScope.launch {
            movieDetailViewModel.getMovies()
        }

        movieDataShow()
    }

    private fun movieDataShow() {
        movieDetailViewModel.enterLikeStatus.observe(this) {
            adapter.differ.submitList(it)
        }
    }
}