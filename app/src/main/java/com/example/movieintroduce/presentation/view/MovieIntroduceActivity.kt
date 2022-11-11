package com.example.movieintroduce.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.presentation.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.databinding.ActivityMovieIntroduceBinding
import com.example.domain.model.Movie
import com.example.domain.util.Resource
import com.example.movieintroduce.presentation.viewmodel.MovieIntroduceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieIntroduceActivity : AppCompatActivity() {
    private val adapter : MovieIntroduceAdapter by lazy {
        MovieIntroduceAdapter {
            val intent = Intent(this@MovieIntroduceActivity, MovieDetailActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }
    private lateinit var binding : ActivityMovieIntroduceBinding
    private val movieIntroduceViewModel: MovieIntroduceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_introduce)

        getMovieIntroduce()

        binding.swipeLayout.setOnRefreshListener {
            getMovieIntroduce()
            binding.swipeLayout.isRefreshing = false
        }
        onClick()
    }

    private fun getMovieIntroduce() {
        movieIntroduceViewModel.getIntroduceMovies("api_key", "ko")
        movieIntroduceViewModel.introduceMovieList.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        showMovieIntroduce(it.movieInfoList)
                    }
                }
                is Resource.Error -> {
                    response.message?.let {
                        Log.e("tag", "에러 $it")
                    }
                }
                is Resource.Loading -> {

                }
            }

        }
    }

    private fun showMovieIntroduce(nowMoviesResponse: List<Movie>) {
        val movieRecycler = binding.movieRecycler
        adapter.differ.submitList(nowMoviesResponse)
        movieRecycler.layoutManager = GridLayoutManager(this@MovieIntroduceActivity, 2)
        movieRecycler.adapter = adapter
    }

    private fun onClick() {
        binding.likeShowBtn.setOnClickListener {
            val intent = Intent(this@MovieIntroduceActivity, MyLikeActivity::class.java)
            startActivity(intent)
        }
    }
}