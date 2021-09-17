package com.example.movieintroduce.presentation.di

import android.app.Application
import com.example.movieintroduce.domain.usecase.CancelLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.GetIntroduceMovieUseCase
import com.example.movieintroduce.domain.usecase.GetLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.LikeMovieUseCase
import com.example.movieintroduce.presentation.viewmodel.MovieFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideMovieViewModelFactory(
        application: Application,
        getIntroduceMovieUseCase: GetIntroduceMovieUseCase,
        getLikeMovieUseCase: GetLikeMovieUseCase,
        likeMovieUseCase: LikeMovieUseCase,
        cancelLikeMovieUseCase: CancelLikeMovieUseCase
    ) : MovieFactory{
        return MovieFactory(
            application,
            getIntroduceMovieUseCase,
            getLikeMovieUseCase,
            likeMovieUseCase,
            cancelLikeMovieUseCase)
    }
}