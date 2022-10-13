package com.example.movieintroduce.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movieintroduce.viewmodel.MovieDetailViewModelFactory
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ActivityMovieDetailBinding
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.repository.MovieRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        val dao = MovieDatabase.getInstance(application).movieDAO
        val repository = MovieRepository(dao)
        val factory = MovieDetailViewModelFactory(repository)
        movieDetailViewModel =
            ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)

        binding.movieViewModel = movieDetailViewModel

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        movieDataShow()
        setLikeStatus()
    }

    private fun movieDataShow() {
        val movieData = intent.getSerializableExtra("item") as Movie

        binding.run {
            toolbar.title = movieData.movieTitle
            setSupportActionBar(toolbar)

            movie = movieData

        }

        lifecycleScope.launch {
            movieDetailViewModel.getMovies().collect {
                it.mapIndexed { index, _ ->
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

    private fun setLikeStatus() {
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