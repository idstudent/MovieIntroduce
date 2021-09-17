package com.example.movieintroduce.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieintroduce.domain.usecase.CancelLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.GetIntroduceMovieUseCase
import com.example.movieintroduce.domain.usecase.GetLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.LikeMovieUseCase

class MovieFactory(
    private val application: Application,
    private val movieUseCase: GetIntroduceMovieUseCase,
    private val getLikeMovieUseCase: GetLikeMovieUseCase,
    private val likeMovieUseCase: LikeMovieUseCase,
    private val cancelLikeMovieUseCase: CancelLikeMovieUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieIntroduceViewModel::class.java)) {
            return MovieIntroduceViewModel(
                application,
                movieUseCase
            ) as T
        }else if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(
                getLikeMovieUseCase,
                likeMovieUseCase,
                cancelLikeMovieUseCase
            ) as T
        }
        throw IllegalAccessException("Unknown view model class")
    }

}