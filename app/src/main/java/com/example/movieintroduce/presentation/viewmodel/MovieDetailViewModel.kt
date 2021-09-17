package com.example.movieintroduce.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.movieintroduce.Event
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.domain.usecase.CancelLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.GetLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.LikeMovieUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getLikeMovieUseCase : GetLikeMovieUseCase,
    private val likeMovieUseCase : LikeMovieUseCase,
    private val cancelLikeMovieUseCase: CancelLikeMovieUseCase
) : ViewModel() {
    private var likeStatus = MutableLiveData<Event<Boolean>>()

    val like : LiveData<Event<Boolean>>
        get() = likeStatus

    fun likeMovieInsert(movie : Movie) : Job {
        return viewModelScope.launch {
            likeMovieUseCase.execute(movie)
//            repository.insert(
//                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
//                    movie.movieMainImg, movie.releaseDate, movie.movieTitle,
//                    movie.movieGrade)
//            )
            likeStatus.value = Event(true)
        }
    }

    fun likeMovieDelete(movie : Movie) : Job {
        return viewModelScope.launch {
            cancelLikeMovieUseCase.execute(movie)
//            repository.delete(
//                Movie(movie.movieId, movie.detailImg,movie.movieOverView,
//                    movie.movieMainImg, movie.releaseDate, movie.movieTitle,
//                    movie.movieGrade)
//            )
            likeStatus.value = Event(false)
        }
    }
    fun getMovies() : LiveData<List<Movie>> {
        return liveData {
            getLikeMovieUseCase.execute().collect {
                emit(it)
            }
//            repository.movies.collect {
//                emit(it)
//            }
        }
    }
}