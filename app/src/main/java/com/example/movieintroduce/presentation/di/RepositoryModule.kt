package com.example.movieintroduce.presentation.di

import com.example.movieintroduce.data.repository.MovieRepositoryImpl
import com.example.movieintroduce.data.repository.dataSource.MovieRemoteDataSource
import com.example.movieintroduce.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource
    ) : MovieRepository{
        return MovieRepositoryImpl(movieRemoteDataSource)
    }
}