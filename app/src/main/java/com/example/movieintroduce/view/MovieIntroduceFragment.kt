package com.example.movieintroduce.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.databinding.FragmentMovieIntroduceBinding
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.repository.MovieRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import com.example.movieintroduce.viewmodel.MovieDetailViewModelFactory
import com.example.movieintroduce.viewmodel.MovieIntroduceViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieIntroduceFragment : Fragment() {
    private val adapter: MovieIntroduceAdapter by lazy {
        MovieIntroduceAdapter { item ->
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }

    private lateinit var binding: FragmentMovieIntroduceBinding
    private lateinit var movieIntroduceViewModel: MovieIntroduceViewModel
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_introduce, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = MovieDatabase.getInstance(requireActivity()).movieDAO
        val repository = MovieRepository(dao)
        val factory = MovieDetailViewModelFactory(repository)

        movieIntroduceViewModel = ViewModelProvider(this)[MovieIntroduceViewModel::class.java]
        movieDetailViewModel =
            ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]


        binding.run {
            movieRecycler.layoutManager = GridLayoutManager(activity, 2)
            movieRecycler.adapter = adapter

            swipeLayout.setOnRefreshListener {
                getMovieIntroduce()
                binding.swipeLayout.isRefreshing = false
            }
        }

        getMovieIntroduce()
    }

    private fun getMovieIntroduce() {
//
//        lifecycleScope.launch(CoroutineExceptionHandler { _, throwable ->
//            Log.e("testljy", "throw ${throwable.printStackTrace()}")
//        }) {
//            val test = movieIntroduceViewModel.test()
//            Log.e("testljy","${test}")
//        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieIntroduceViewModel.getMovieIntroduces().collect {
                    adapter.submitData(it)
                }
            }
        }
    }
}
