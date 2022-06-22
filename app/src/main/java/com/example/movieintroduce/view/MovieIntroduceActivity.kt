package com.example.movieintroduce.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.databinding.ActivityMovieIntroduceBinding
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.model.MovieDBRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import com.example.movieintroduce.viewmodel.MovieIntroduceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieIntroduceActivity : BaseActivity<ActivityMovieIntroduceBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_movie_introduce
    private val adapter: MovieIntroduceAdapter by lazy {
        MovieIntroduceAdapter { item ->
            val intent = Intent(this@MovieIntroduceActivity, MovieDetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }

    private val movieIntroduceViewModel: MovieIntroduceViewModel by viewModels()

    override fun initView() {
        super.initView()

        binding.run {
            movieRecycler.layoutManager = GridLayoutManager(this@MovieIntroduceActivity, 2)
            movieRecycler.adapter = adapter
        }
    }

    override fun initListener() {
        super.initListener()

        binding. run {
            swipeLayout.setOnRefreshListener {
                getMovieIntroduce()
                swipeLayout.isRefreshing = false
            }
            likeShowBtn.setOnClickListener {
                val intent = Intent(this@MovieIntroduceActivity, MyLikeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        getMovieIntroduce()
    }
    private fun getMovieIntroduce() {
        movieIntroduceViewModel.getMovieIntroduces()
            .observe(this) {
                adapter.submitData(this.lifecycle, it)
            }
    }
}