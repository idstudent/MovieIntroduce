package com.example.movieintroduce.presentation.di

import com.example.movieintroduce.presentation.adapter.MovieIntroduceAdapter
import com.example.movieintroduce.presentation.adapter.MyLikeMovieAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideMovieIntroduceAdapter() : MovieIntroduceAdapter {
        return MovieIntroduceAdapter()
    }

    @Singleton
    @Provides
    fun provideMyLikeMovieAdapter() : MyLikeMovieAdapter {
        return MyLikeMovieAdapter()
    }
}