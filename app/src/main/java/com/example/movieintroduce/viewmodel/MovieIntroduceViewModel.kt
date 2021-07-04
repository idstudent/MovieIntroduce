package com.example.movieintroduce.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movieintroduce.model.MovieInfo
import com.example.movieintroduce.model.MovieIntroduceRepository

class MovieIntroduceViewModel(application: Application) : AndroidViewModel(application){
    private var movieIntroduceRepository = MovieIntroduceRepository()

    fun getMovieIntroduces() : LiveData<List<MovieInfo>>{
        return movieIntroduceRepository.getNowMovies()
    }
}