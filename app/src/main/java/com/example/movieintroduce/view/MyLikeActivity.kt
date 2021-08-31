package com.example.movieintroduce.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MyLikeMovieAdapter
import com.example.movieintroduce.databinding.ActivityMyLikeBinding
import com.example.movieintroduce.db.Movie
import com.example.movieintroduce.listener.ItemClickListener
import com.example.movieintroduce.viewmodel.MovieDetailViewModel

class MyLikeActivity : AppCompatActivity() {
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var adapter  : MyLikeMovieAdapter
    private lateinit var binding : ActivityMyLikeBinding
    private var movieIdList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_like)
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        getLikeMovies()
    }

    private fun getLikeMovies() {
        movieDetailViewModel.getMovies().observe(this, Observer {
            movieIdList.clear()
            // 좋아요 체크
            for(i in it.indices) {
                movieIdList.add(it[i].movieId)
            }
            showMyLikeMovies(it)
        })
    }
    private fun showMyLikeMovies(nowMoviesResponse: List<Movie>) {
        val movieRecycler = binding.likeRecycler
        adapter = MyLikeMovieAdapter(this@MyLikeActivity, nowMoviesResponse)
        adapter.itemClickListener(listener)
        movieRecycler.layoutManager = GridLayoutManager(this@MyLikeActivity, 2)
        movieRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    val listener = object : ItemClickListener<Movie> {
        override fun onClick(item: Movie) {
            val intent = Intent(this@MyLikeActivity, MyLikeMovieDetailActivity::class.java)
            intent.putExtra("item", item)
            intent.putExtra("id", movieIdList)
            startActivity(intent)
        }
    }
}