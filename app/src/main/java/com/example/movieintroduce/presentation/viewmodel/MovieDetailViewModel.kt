package com.example.movieintroduce.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.movieintroduce.Event
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.domain.usecase.CancelLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.GetLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.LikeMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
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

            likeStatus.value = Event(true)
        }
    }

    fun likeMovieDelete(movie : Movie) : Job {
        return viewModelScope.launch {
            cancelLikeMovieUseCase.execute(movie)

            likeStatus.value = Event(false)
        }
    }
    fun getMovies() : LiveData<List<Movie>> {
        return liveData {
            getLikeMovieUseCase.execute().collect {
                emit(it)
            }
        }
    }
}