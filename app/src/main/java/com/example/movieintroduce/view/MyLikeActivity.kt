package com.example.movieintroduce.view

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MyLikeMovieAdapter
import com.example.movieintroduce.databinding.ActivityMyLikeBinding
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyLikeActivity : BaseActivity<ActivityMyLikeBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_my_like

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private val adapter : MyLikeMovieAdapter by lazy {
        MyLikeMovieAdapter { item ->
            val intent = Intent(this@MyLikeActivity, MovieDetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()

        binding.run {
            likeRecycler.layoutManager = GridLayoutManager(this@MyLikeActivity, 2)
            likeRecycler.adapter = adapter
        }
    }
    override fun initViewModel() {
        super.initViewModel()

        lifecycleScope.launch {
            movieDetailViewModel.getMovies().collect {
                adapter.submitList(it)
            }
        }
    }
}