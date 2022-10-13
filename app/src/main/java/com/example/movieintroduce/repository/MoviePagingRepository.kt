package com.example.movieintroduce.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieintroduce.model.Movie
import kotlinx.coroutines.flow.Flow

class MoviePagingRepository {
    fun getMoviePaging(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource() }
        ).flow
    }
}