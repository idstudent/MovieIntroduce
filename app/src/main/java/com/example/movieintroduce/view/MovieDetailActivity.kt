package com.example.movieintroduce.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ActivityMovieDetailBinding
import com.example.movieintroduce.getGenre
import com.example.movieintroduce.model.MovieInfo

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        movieDataShow()
    }
    private fun movieDataShow() {
        val intent = intent
        val movieData = intent.getSerializableExtra("item") as MovieInfo

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = movieData.movieTitle
        setSupportActionBar(toolbar)

        binding.movie = movieData

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
}