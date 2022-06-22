package com.example.movieintroduce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieintroduce.model.Movie
import com.example.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MovieIntroduceViewModel : ViewModel() {
    fun getMovieIntroduces() : Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = 20)) {
            MoviePagingSource()
        }.flow
    }
}