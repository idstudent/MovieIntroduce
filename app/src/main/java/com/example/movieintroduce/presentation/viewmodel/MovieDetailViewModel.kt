package com.example.movieintroduce.presentation.viewmodel

import androidx.lifecycle.*
import com.example.movieintroduce.Event
import com.example.movieintroduce.data.db.Movie
import com.example.movieintroduce.data.model.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private var likeStatus = MutableLiveData<Event<Boolean>>()

    val like : LiveData<Event<Boolean>>
        get() = likeStatus

    fun likeMovieInsert(movie : Movie) : Job {
        return viewModelScope.launch {
            repository.insert(
                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
                    movie.movieMainImg, movie.releaseDate, movie.movieTitle,
                    movie.movieGrade))
            likeStatus.value = Event(true)
        }
    }

    fun likeMovieDelete(movie : Movie) : Job {
        return viewModelScope.launch {
            repository.delete(
                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
                    movie.movieMainImg, movie.releaseDate, movie.movieTitle,
                    movie.movieGrade))
            likeStatus.value = Event(false)
        }
    }
    fun getMovies() : LiveData<List<Movie>> {
        return liveData {
            repository.movies.collect {
                emit(it)
            }
        }
    }
}