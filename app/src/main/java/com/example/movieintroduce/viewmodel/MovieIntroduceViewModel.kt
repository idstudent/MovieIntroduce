package com.example.movieintroduce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieintroduce.db.Movie
import com.example.movieintroduce.model.MovieIntroduceRepository

class MovieIntroduceViewModel : ViewModel() {
    private var movieIntroduceRepository = MovieIntroduceRepository()

    fun getMovieIntroduces() : LiveData<List<Movie>>{
        return movieIntroduceRepository.getNowMovies()
    }
}