package com.example.movieintroduce.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.domain.model.Movie
import com.example.domain.usecase.GetIntroduceMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MovieIntroduceViewModel @Inject constructor(
    private val getIntroduceMovieUseCase: GetIntroduceMovieUseCase
) : ViewModel() {

    fun getIntroduceMovies(apiKey : String, country : String): Flow<List<Movie>> {
        return getIntroduceMovieUseCase.execute(apiKey, country)
            .catch { it.printStackTrace() }
    }
}