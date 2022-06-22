package com.example.movieintroduce.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieintroduce.api.ApiService
import com.example.movieintroduce.model.Movie

class MoviePagingRepository(
    private val apiService: ApiService
) {
    fun getMoviePaging(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(apiService) }
        ).liveData
    }
}