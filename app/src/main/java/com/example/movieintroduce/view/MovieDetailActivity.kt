package com.example.movieintroduce.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        movieDataShow()
    }
    private fun movieDataShow() {
        val intent = intent
        val movieData = intent.getSerializableExtra("item") as MovieInfo

        for(i in movieData.genre.indices) {
            var genere = getGenre(movieData.genre[i])

            if(i == 0) {
                binding.movieGenre.append(genere)
            }else {
                binding.movieGenre.append("," + genere)
            }
        }
    }
}