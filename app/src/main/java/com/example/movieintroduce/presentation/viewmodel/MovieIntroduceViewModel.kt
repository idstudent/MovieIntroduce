package com.example.movieintroduce.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieintroduce.data.db.Movie
import com.example.movieintroduce.data.model.MovieIntroduceRepository

class MovieIntroduceViewModel : ViewModel() {
    private var movieIntroduceRepository = MovieIntroduceRepository()

    fun getMovieIntroduces() : LiveData<List<Movie>>{
        return movieIntroduceRepository.getNowMovies()
    }
}