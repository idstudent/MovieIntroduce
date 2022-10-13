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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getLikeMovieUseCase : GetLikeMovieUseCase,
    private val likeMovieUseCase : LikeMovieUseCase,
    private val cancelLikeMovieUseCase: CancelLikeMovieUseCase
) : ViewModel() {
    private var _likeStatus = MutableLiveData<Event<Boolean>>()
    val likeStatus : LiveData<Event<Boolean>> get() = _likeStatus

    fun likeMovieInsert(movie : Movie) : Job {
        return viewModelScope.launch {
            likeMovieUseCase.execute(movie)

            _likeStatus.value = Event(true)
        }
    }

    fun likeMovieDelete(movie : Movie) : Job {
        return viewModelScope.launch {
            cancelLikeMovieUseCase.execute(movie)

            _likeStatus.value = Event(false)
        }
    }

    fun getMovies() = getLikeMovieUseCase.execute()

}