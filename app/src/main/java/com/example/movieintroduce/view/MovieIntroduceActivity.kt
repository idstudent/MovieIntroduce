package com.example.movieintroduce.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.viewmodel.MovieDetailViewModelFactory
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.databinding.ActivityMovieIntroduceBinding
import com.example.movieintroduce.db.Movie
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.listener.ItemClickListener
import com.example.movieintroduce.model.MovieRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import com.example.movieintroduce.viewmodel.MovieIntroduceViewModel

class MovieIntroduceActivity : AppCompatActivity() {
    private lateinit var adapter  : MovieIntroduceAdapter
    private lateinit var binding : ActivityMovieIntroduceBinding
    private lateinit var movieIntroduceViewModel: MovieIntroduceViewModel
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var movieIdList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_introduce)

        val dao = MovieDatabase.getInstance(application).movieDAO
        val repository = MovieRepository(dao)
        val factory = MovieDetailViewModelFactory(repository)

        movieIntroduceViewModel = ViewModelProvider(this).get(MovieIntroduceViewModel::class.java)
        movieDetailViewModel = ViewModelProvider(this,factory).get(MovieDetailViewModel::class.java)

        getMovieIntroduce()

        binding.swipeLayout.setOnRefreshListener {
            getMovieIntroduce()
            binding.swipeLayout.isRefreshing = false
        }
        onClick()
    }

    override fun onResume() {
        super.onResume()

        getLike()
    }
    private fun getMovieIntroduce() {
        movieIntroduceViewModel.getMovieIntroduces().observe(this, Observer<List<Movie>> { t ->
            showMovieIntroduce(t)
        })
    }

    // 좋아요 체크
    private fun getLike() {
        movieDetailViewModel.getMovies().observe(this, Observer {
            movieIdList.clear()
            for(i in it.indices) {
                movieIdList.add(it[i].movieId)
            }
        })
    }
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