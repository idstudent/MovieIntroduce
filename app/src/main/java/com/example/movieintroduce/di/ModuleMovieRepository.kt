package com.example.movieintroduce.di

import com.example.movieintroduce.paging.MoviePagingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleMovieRepository {
    @Singleton
    @Provides
    fun provideMoviePagingRepository() : MoviePagingRepository {
        return MoviePagingRepository()
    }
}