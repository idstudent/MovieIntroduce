package com.example.movieintroduce.presentation.di

import com.example.data.repository.MovieRepositoryImpl
import com.example.data.repository.dataSource.MovieLocalDataSource
import com.example.data.repository.dataSource.MovieRemoteDataSource
import com.example.domain.repository.MovieRepository
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
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ) : MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }
}