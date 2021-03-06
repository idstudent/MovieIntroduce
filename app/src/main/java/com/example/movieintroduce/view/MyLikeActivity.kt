package com.example.movieintroduce.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.viewmodel.MovieDetailViewModelFactory
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MyLikeMovieAdapter
import com.example.movieintroduce.databinding.ActivityMyLikeBinding
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.repository.MovieRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.launch

class MyLikeActivity : AppCompatActivity() {
    private lateinit var movieDetailViewModel: MovieDetailViewModel
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

        val dao = MovieDatabase.getInstance(application).movieDAO
        val repository = MovieRepository(dao)
        val factory = MovieDetailViewModelFactory(repository)

        movieDetailViewModel = ViewModelProvider(this,factory).get(MovieDetailViewModel::class.java)

        binding.run {
            likeRecycler.layoutManager = GridLayoutManager(this@MyLikeActivity, 2)
            likeRecycler.adapter = adapter
        }

        movieDataShow()
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            movieDetailViewModel.getMovies()
        }
    }
    private fun movieDataShow() {
        movieDetailViewModel.enterLikeStatus.observe(this, Observer {
            adapter.differ.submitList(it)
        })
    }
}