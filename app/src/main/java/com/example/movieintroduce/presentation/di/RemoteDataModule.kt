package com.example.movieintroduce.presentation.di

import com.example.movieintroduce.data.api.ApiService
import com.example.movieintroduce.data.repository.dataSource.MovieRemoteDataSource
import com.example.movieintroduce.data.repository.dataSourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(apiService: ApiService) : MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiService)
    }
}