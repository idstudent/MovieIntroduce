package com.example.movieintroduce.repository

import com.example.movieintroduce.db.MovieDAO
import com.example.movieintroduce.model.Movie

class MovieDBRepository(private val dao : MovieDAO) {
    val movies = dao.selectMovies()

    suspend fun insert(movie : Movie) {
        return dao.insertMovie(movie)
    }

    suspend fun delete(movie : Movie) {
        return dao.deleteMovie(movie)
    }
}