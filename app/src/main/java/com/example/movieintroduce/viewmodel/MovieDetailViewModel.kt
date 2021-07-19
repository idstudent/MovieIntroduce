package com.example.movieintroduce.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.movieintroduce.Event
import com.example.movieintroduce.db.Movie
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.model.MovieInfo
import com.example.movieintroduce.model.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private var movieRepository = MovieRepository(MovieDatabase.getInstance(application).movieDAO)
    private var likeStatus = MutableLiveData<Event<Boolean>>()

    val like : LiveData<Event<Boolean>>
        get() = likeStatus

    fun insert(movie : MovieInfo) : Job {
        return viewModelScope.launch {
            movieRepository.insert(
                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
                        movie.movieMainImg, movie.releaseDate, movie.movieTitle,
                        movie.movieGrade))
            likeStatus.value = Event(true)
        }
    }
    fun delete(movie : MovieInfo) : Job {
        return viewModelScope.launch {
            movieRepository.delete(
                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
                    movie.movieMainImg, movie.releaseDate, movie.movieTitle,
                    movie.movieGrade))
            likeStatus.value = Event(false)
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