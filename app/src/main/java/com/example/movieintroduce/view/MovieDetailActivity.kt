package com.example.movieintroduce.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieintroduce.Event
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ActivityMovieDetailBinding
import com.example.movieintroduce.db.Movie
import com.example.movieintroduce.getGenre
import com.example.movieintroduce.model.MovieInfo
import com.example.movieintroduce.viewmodel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        binding.movieViewModel = movieDetailViewModel
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        movieDataShow()
    }

    override fun onResume() {
        super.onResume()

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

        if(idList.size>0) {
            for (i in 0 until idList.size) {
                if (movieData.movieId == idList[i]){
                    binding.movieRememberBtn.visibility = View.VISIBLE
                    binding.movieNoRememberBtn.visibility = View.INVISIBLE
                    return
                }else {
                    binding.movieRememberBtn.visibility = View.INVISIBLE
                    binding.movieNoRememberBtn.visibility = View.VISIBLE
                }
            }
        }else {
            binding.movieRememberBtn.visibility = View.INVISIBLE
            binding.movieNoRememberBtn.visibility = View.VISIBLE
        }
//        for(i in movieData.genre.indices) {
//            var genere = getGenre(movieData.genre[i])
//
//            if(i == 0) {
//                binding.movieGenre.append(genere)
//            }else {
//                binding.movieGenre.append("," + genere)
//            }
//        }
    }

    private fun setLikeStatus() {
        movieDetailViewModel.like.observe(this, Observer {
            it.getContentIfNotHandled()?.let{ status ->
                if(status) {
                    binding.movieNoRememberBtn.visibility = View.INVISIBLE
                    binding.movieRememberBtn.visibility = View.VISIBLE
                }else {
                    binding.movieNoRememberBtn.visibility = View.VISIBLE
                    binding.movieRememberBtn.visibility = View.INVISIBLE
                }
            }
        })
    }
}