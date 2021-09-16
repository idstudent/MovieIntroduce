package com.example.movieintroduce.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.BuildConfig
import com.example.movieintroduce.presentation.viewmodel.MovieFactory
import com.example.movieintroduce.R
import com.example.movieintroduce.presentation.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.databinding.ActivityMovieIntroduceBinding
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.data.model.NowMoviesResponse
import com.example.movieintroduce.listener.ItemClickListener
import com.example.movieintroduce.data.util.Resource
import com.example.movieintroduce.presentation.viewmodel.MovieDetailViewModel
import com.example.movieintroduce.presentation.viewmodel.MovieIntroduceViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieIntroduceActivity : AppCompatActivity() {
    @Inject
    lateinit var factory : MovieFactory

    private lateinit var adapter  : MovieIntroduceAdapter
    private lateinit var binding : ActivityMovieIntroduceBinding
    private lateinit var movieIntroduceViewModel: MovieIntroduceViewModel
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var movieIdList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_introduce)

//        val dao = MovieDatabase.getInstance(application).movieDAO
//        val repository = MovieRepository(dao)
//        val factory = MovieFactory(repository)

        movieIntroduceViewModel = ViewModelProvider(this,factory).get(MovieIntroduceViewModel::class.java)
//        movieDetailViewModel = ViewModelProvider(this,factory).get(MovieDetailViewModel::class.java)

        getMovieIntroduce()

        binding.swipeLayout.setOnRefreshListener {
            getMovieIntroduce()
            binding.swipeLayout.isRefreshing = false
        }
        onClick()
    }

    override fun onResume() {
        super.onResume()

//        getLike()
    }
    private fun getMovieIntroduce() {
        movieIntroduceViewModel.getIntroduceMovies(BuildConfig.api_key, "ko")
        movieIntroduceViewModel.introduceMovieList.observe(this, Observer<Resource<NowMoviesResponse>> {
                response ->
            when(response) {
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

        })
    }

    //    // 좋아요 체크
//    private fun getLike() {
//        movieDetailViewModel.getMovies().observe(this, Observer {
//            movieIdList.clear()
//            for(i in it.indices) {
//                movieIdList.add(it[i].movieId)
//            }
//        })
//    }
    private fun showMovieIntroduce(nowMoviesResponse: List<Movie>) {

        val movieRecycler = binding.movieRecycler
        adapter = MovieIntroduceAdapter(this@MovieIntroduceActivity, nowMoviesResponse)
        adapter.itemClickListener(listener)
        movieRecycler.layoutManager = GridLayoutManager(this@MovieIntroduceActivity, 2)
        movieRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    val listener = object : ItemClickListener<Movie> {
        override fun onClick(item: Movie) {
            val intent = Intent(this@MovieIntroduceActivity, MovieDetailActivity::class.java)
            intent.putExtra("item", item)
            intent.putExtra("id", movieIdList)
            startActivity(intent)
        }
    }
    private fun onClick() {
        binding.likeShowBtn.setOnClickListener {
            val intent = Intent(this@MovieIntroduceActivity, MyLikeActivity::class.java)
            startActivity(intent)
        }
    }
}