package com.example.movieintroduce.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
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

        lifecycleScope.launch {
            movieDetailViewModel.getMovies()
        }

        movieDataShow()
        setLikeStatus()
    }

    private fun movieDataShow() {
        val intent = intent
        val movieData = intent.getSerializableExtra("item") as Movie

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = movieData.movieTitle
        setSupportActionBar(toolbar)

        binding.movie = movieData

        movieDetailViewModel.enterLikeStatus.observe(this, Observer {
            run loop@{
                it.map {
                    if (movieData.movieId == it.movieId) {
                        movieRememberBtn(true)
                        return@loop
                    } else {
                        movieRememberBtn(false)
                    }
                }
            }
        })
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
        if(remember) {
            binding.movieRememberBtn.visibility = View.VISIBLE
            binding.movieNoRememberBtn.visibility = View.INVISIBLE
        }else {
            binding.movieRememberBtn.visibility = View.INVISIBLE
            binding.movieNoRememberBtn.visibility = View.VISIBLE
        }
    }
}