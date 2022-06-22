package com.example.movieintroduce.di

import android.content.Context
import androidx.room.Room
import com.example.movieintroduce.db.MovieDAO
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.repository.MovieDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleMovieDatabase() {
    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context : Context) : MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDAO(movieDB : MovieDatabase) : MovieDAO {
        return movieDB.movieDAO()
    }

    @Singleton
    @Provides
    fun provideMovieDBRepository(movieDAO: MovieDAO) : MovieDBRepository {
        return MovieDBRepository(movieDAO)
    }
}