package com.example.movieintroduce.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieintroduce.domain.usecase.GetIntroduceMovieUseCase

class MovieFactory(
    private val application: Application,
    private val movieUseCase: GetIntroduceMovieUseCase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieIntroduceViewModel::class.java)) {
            return MovieIntroduceViewModel(
                application,
                movieUseCase
            ) as T
        }
        throw IllegalAccessException("Unknown view model class")
    }

}