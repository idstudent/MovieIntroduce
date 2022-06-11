package com.example.movieintroduce.viewmodel

import androidx.lifecycle.*
import com.example.movieintroduce.Event
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.model.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private var _clickLikeStatus = MutableLiveData<Event<Boolean>>()
    val clickLikeStatus : LiveData<Event<Boolean>> get() = _clickLikeStatus

    private var _enterLikeStatus = MutableLiveData<List<Movie>>()
    val enterLikeStatus : LiveData<List<Movie>> get() = _enterLikeStatus


    fun likeMovieInsert(movie : Movie) : Job {
        return viewModelScope.launch {
            repository.insert(
                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
                    movie.movieMainImg, movie.releaseDate, movie.movieTitle,
                    movie.movieGrade)
            )
            _clickLikeStatus.value = Event(true)
        }
    }

    fun likeMovieDelete(movie : Movie) : Job {
        return viewModelScope.launch {
            repository.delete(
                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
                    movie.movieMainImg, movie.releaseDate, movie.movieTitle,
                    movie.movieGrade)
            )
            _clickLikeStatus.value = Event(false)
        }
    }
    suspend fun getMovies()  {
        repository.movies.collect {
            _enterLikeStatus.value = it
        }
    }
}