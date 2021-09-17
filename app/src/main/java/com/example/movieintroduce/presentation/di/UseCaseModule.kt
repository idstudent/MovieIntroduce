package com.example.movieintroduce.presentation.di

import com.example.movieintroduce.domain.repository.MovieRepository
import com.example.movieintroduce.domain.usecase.CancelLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.GetIntroduceMovieUseCase
import com.example.movieintroduce.domain.usecase.GetLikeMovieUseCase
import com.example.movieintroduce.domain.usecase.LikeMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetIntroduceMovieUseCase(movieRepository: MovieRepository) : GetIntroduceMovieUseCase {
        return GetIntroduceMovieUseCase(movieRepository)
    }
    @Singleton
    @Provides
    fun provideGetLikeMovieUseCase(movieRepository: MovieRepository) : GetLikeMovieUseCase {
        return GetLikeMovieUseCase(movieRepository)
    }
    @Singleton
    @Provides
    fun provideLikeMovieUseCase(movieRepository: MovieRepository) : LikeMovieUseCase {
        return LikeMovieUseCase(movieRepository)
    }
    @Singleton
    @Provides
    fun provideCancelLikeMovieUseCase(movieRepository: MovieRepository) : CancelLikeMovieUseCase {
        return CancelLikeMovieUseCase(movieRepository)
    }
}