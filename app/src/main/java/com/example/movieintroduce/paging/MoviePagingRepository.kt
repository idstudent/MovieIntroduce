package com.example.movieintroduce.paging

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieintroduce.model.Movie
import dagger.Provides
import javax.inject.Singleton

class MoviePagingRepository {
    fun getMoviePaging(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource() }
        ).liveData
    }
}