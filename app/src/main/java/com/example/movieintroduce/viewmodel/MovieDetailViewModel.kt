package com.example.movieintroduce.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movieintroduce.db.Movie
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.model.MovieInfo
import com.example.movieintroduce.model.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private var movieRepository = MovieRepository(MovieDatabase.getInstance(application).movieDAO)

    fun insert(movie : MovieInfo) : Job {
        Log.e("tag", "여기탐" + movie)
        return viewModelScope.launch {
            movieRepository.insert(Movie(0,movie))
        }
    }
    fun getMovies() : LiveData<List<Movie>> {
        return liveData {
            movieRepository.movies.collect {
                emit(it)
            }
        }
    }
}