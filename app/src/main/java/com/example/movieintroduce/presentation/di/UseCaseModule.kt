package com.example.movieintroduce.presentation.di

import com.example.movieintroduce.domain.repository.MovieRepository
import com.example.movieintroduce.domain.usecase.GetIntroduceMovieUseCase
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
}