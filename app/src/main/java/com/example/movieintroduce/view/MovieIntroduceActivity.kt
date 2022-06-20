package com.example.movieintroduce.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieintroduce.viewmodel.MovieDetailViewModelFactory
import com.example.movieintroduce.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.databinding.ActivityMovieIntroduceBinding
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.model.MovieDBRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import com.example.movieintroduce.viewmodel.MovieIntroduceViewModel
import kotlinx.coroutines.flow.Flow


class MovieIntroduceActivity : AppCompatActivity() {
    private val adapter: MovieIntroduceAdapter by lazy {
        MovieIntroduceAdapter { item ->
            val intent = Intent(this@MovieIntroduceActivity, MovieDetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMovieIntroduceBinding
    private lateinit var movieIntroduceViewModel: MovieIntroduceViewModel
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_introduce)
        val dao = MovieDatabase.getInstance(application).movieDAO
        val repository = MovieDBRepository(dao)
        val factory = MovieDetailViewModelFactory(repository)

        movieIntroduceViewModel = ViewModelProvider(this).get(MovieIntroduceViewModel::class.java)
        movieDetailViewModel =
            ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)

        getMovieIntroduce()

//        binding.swipeLayout.setOnRefreshListener {
//            getMovieIntroduce()
//            binding.swipeLayout.isRefreshing = false
//        }
        onClick()
    }

    private fun getMovieIntroduce() {
        val movies = movieIntroduceViewModel.getMovieIntroduces()

        setContent {
            Conversation(movies = movies)
        }
    }
//    private fun getMovieIntroduce() {
//        movieIntroduceViewModel.getMovieIntroduces()
//            .observe(this) { movies ->
//            }
//    }

//    private fun showMovieIntroduce(nowMoviesResponse: PagingData<Movie>) {
//        val movieRecycler = binding.movieRecycler
//        adapter.submitData(this.lifecycle, nowMoviesResponse)
//        movieRecycler.layoutManager = GridLayoutManager(thsdsis@MovieIntroduceActivity, 2)
//        movieRecycler.adapter = adapter
//    }

    private fun onClick() {
//        binding.likeShowBtn.setOnClickListener {
//            val intent = Intent(this@MovieIntroduceActivity, MyLikeActivity::class.java)
//            startActivity(intent)
//        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Conversation(movies: Flow<PagingData<Movie>>) {
        val movieItems: LazyPagingItems<Movie> = movies.collectAsLazyPagingItems()
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(movieItems.itemCount) { index ->
                movieItems[index]?.let {
                    Text(it.movieTitle)
                }
            }

//            items(movieItems) { movies ->
//                Log.e("moviesljy", "$movies")
//            }
        }
    }
}
