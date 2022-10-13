package com.example.movieintroduce.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ActivityMyLikeMovieDetailBinding
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.presentation.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyLikeMovieDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyLikeMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_like_movie_detail)

        binding.movieViewModel = movieDetailViewModel

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
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

        lifecycleScope.launch {
            movieDetailViewModel.getMovies().collect {
                if(it.isEmpty()) {
                    setOffRememberBtn()
                }else {
                    it.mapIndexed { index, _ ->
                        if (movieData.movieId == it[index].movieId){
                            setOnRememberBtn()
                            return@mapIndexed
                        }else {
                            setOffRememberBtn()
                        }
                    }
                }
            }
        }
    }

    private fun setLikeStatus() {
        movieDetailViewModel.likeStatus.observe(this, Observer {
            it.getContentIfNotHandled()?.let{ status ->
                if(status) {
                    setOnRememberBtn()
                }else {
                    setOffRememberBtn()
                }
            }
        })
    }

    private fun setOnRememberBtn() {
        binding.run {
            movieRememberBtn.visibility = View.VISIBLE
            movieNoRememberBtn.visibility = View.INVISIBLE
        }
    }

    private fun setOffRememberBtn() {
        binding.run {
            movieRememberBtn.visibility = View.INVISIBLE
            movieNoRememberBtn.visibility = View.VISIBLE
        }
    }
}