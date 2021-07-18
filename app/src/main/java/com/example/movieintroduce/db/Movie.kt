package com.example.movieintroduce.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieintroduce.model.MovieInfo

@Entity(tableName = "movie_table")
data class Movie (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @Embedded
    var movie : MovieInfo
)