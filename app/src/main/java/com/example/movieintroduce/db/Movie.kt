package com.example.movieintroduce.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieintroduce.model.MovieInfo
import java.io.Serializable

@Entity(tableName = "movie_table")
data class Movie (
    @PrimaryKey
    var movieId : Int,
    @ColumnInfo(name = "detail_img")
    var detailImg : String,
    @ColumnInfo(name = "overview")
    var movieOverView : String,
    @ColumnInfo(name = "poster_path")
    var movieMainImg : String,
    @ColumnInfo(name = "release_date")
    var releaseDate : String,
    @ColumnInfo(name = "title")
    var movieTitle : String,
    @ColumnInfo(name = "vote_average")
    var movieGrade : Double
):Serializable