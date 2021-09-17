package com.example.movieintroduce.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.movieintroduce.data.db.MovieDAO
import com.example.movieintroduce.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(app:Application) : MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, "movie_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun provideMovieDao(articleDatabase: MovieDatabase) : MovieDAO {
        return articleDatabase.getMovieDAO()
    }
}