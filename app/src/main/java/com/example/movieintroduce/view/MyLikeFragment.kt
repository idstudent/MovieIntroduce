package com.example.movieintroduce.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieintroduce.R
import com.example.movieintroduce.adapter.MyLikeMovieAdapter
import com.example.movieintroduce.databinding.FragmentMovieIntroduceBinding
import com.example.movieintroduce.databinding.FragmentMyLikeBinding
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.repository.MovieRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import com.example.movieintroduce.viewmodel.MovieDetailViewModelFactory
import kotlinx.coroutines.launch

class MyLikeFragment : Fragment() {
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private val adapter : MyLikeMovieAdapter by lazy {
        MyLikeMovieAdapter { item ->
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }

    private lateinit var binding: FragmentMyLikeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_like, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = MovieDatabase.getInstance(requireActivity()).movieDAO
        val repository = MovieRepository(dao)
        val factory = MovieDetailViewModelFactory(repository)

        movieDetailViewModel = ViewModelProvider(this,factory).get(MovieDetailViewModel::class.java)

        binding.run {
            likeRecycler.layoutManager = GridLayoutManager(activity, 2)
            likeRecycler.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieDetailViewModel.getMovies().collect {
                    adapter.differ.submitList(it)
                }
            }
        }
    }
}
