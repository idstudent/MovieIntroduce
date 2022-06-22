package com.example.movieintroduce.view

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ActivityMovieDetailBinding
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_movie_detail

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun initView() {
        super.initView()

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val movieData = intent.getSerializableExtra("item") as Movie

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = movieData.movieTitle
        setSupportActionBar(toolbar)

        binding.movie = movieData
        binding.movieViewModel = movieDetailViewModel

        lifecycleScope.launch {
            movieDetailViewModel.getMovies()
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        movieDetailViewModel.enterLikeStatus.observe(this, Observer {
            run loop@{
                it.map {
                    if (binding.movie?.movieId == it.movieId) {
                        movieRememberBtn(true)
                        return@loop
                    } else {
                        movieRememberBtn(false)
                    }
                }
            }
        })

        movieDetailViewModel.clickLikeStatus.observe(this, Observer {
            it.getContentIfNotHandled()?.let { status ->
                if (status) {
                    movieRememberBtn(true)
                } else {
                    movieRememberBtn(false)
                }
            }
        })
    }

    private fun movieRememberBtn(remember : Boolean) {
        if(remember) {
            binding.movieRememberBtn.visibility = View.VISIBLE
            binding.movieNoRememberBtn.visibility = View.INVISIBLE
        }else {
            binding.movieRememberBtn.visibility = View.INVISIBLE
            binding.movieNoRememberBtn.visibility = View.VISIBLE
        }
    }
}