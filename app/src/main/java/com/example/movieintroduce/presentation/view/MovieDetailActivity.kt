package com.example.movieintroduce.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ActivityMovieDetailBinding
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.presentation.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

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
        val idList = intent.getSerializableExtra("id") as ArrayList<Int>

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = movieData.movieTitle
        setSupportActionBar(toolbar)

        binding.movie = movieData

        if(idList.size > 0) {
            idList.mapIndexed { index, _ ->
                if (movieData.movieId == idList[index]){
                    setOnRememberBtn()
                    return
                }else {
                    setOffRememberBtn()
                }
            }
        }else {
            setOffRememberBtn()
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