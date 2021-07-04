package com.example.movieintroduce.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.databinding.ActivityMainBinding
import com.example.movieintroduce.model.MovieInfo
import com.example.movieintroduce.viewmodel.MovieIntroduceViewModel

class MovieIntroduceActivity : AppCompatActivity() {
    private lateinit var adapter  : MovieIntroduceAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var movieIntroduceViewModel: MovieIntroduceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        movieIntroduceViewModel = ViewModelProvider(this).get(MovieIntroduceViewModel::class.java)

        getMovieIntroduce()
    }

    private fun getMovieIntroduce() {
        movieIntroduceViewModel.getMovieIntroduces().observe(this, Observer<List<MovieInfo>> { t ->
            showMovieIntroduce(t)
        })
    }
    private fun showMovieIntroduce(nowMoviesResponse: List<MovieInfo>) {
        val movieRecycler = binding.movieRecycler
        adapter = MovieIntroduceAdapter(this@MovieIntroduceActivity, nowMoviesResponse)
        movieRecycler.layoutManager = GridLayoutManager(this@MovieIntroduceActivity, 2)
        movieRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}