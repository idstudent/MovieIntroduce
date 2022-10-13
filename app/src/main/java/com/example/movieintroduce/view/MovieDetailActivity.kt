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

        binding.run {
            toolbar.title = movieData.movieTitle
            setSupportActionBar(toolbar)

            movie = movieData
            movieViewModel = movieDetailViewModel
        }

        lifecycleScope.launch {
            movieDetailViewModel.getMovies().collect {
                it.mapIndexed { index, movie ->
                    if (movieData.movieId == it[index].movieId) {
                        movieRememberBtn(true)
                        return@collect
                    } else {
                        movieRememberBtn(false)
                    }
                }
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()

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
        binding.run {
            if(remember) {
                movieRememberBtn.visibility = View.VISIBLE
                movieNoRememberBtn.visibility = View.INVISIBLE
            }else {
                movieRememberBtn.visibility = View.INVISIBLE
                movieNoRememberBtn.visibility = View.VISIBLE
            }
        }

    }
}