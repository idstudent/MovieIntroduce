package com.example.movieintroduce.presentation.di

import com.example.data.db.MovieDAO
import com.example.data.repository.dataSource.MovieLocalDataSource
import com.example.data.repository.dataSourceImpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(movieDAO: MovieDAO) : MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDAO)
    }
}