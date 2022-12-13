package com.example.movieintroduce.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NowMoviesResponse (
        @SerializedName("page")
        var page : Int,
        @SerializedName("results")
        var movieInfoList : ArrayList<Movie>
)

@Entity(tableName = "movie_table")
data class Movie (
    @PrimaryKey
    @SerializedName("id")
    var movieId : Int,

    @ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids")
    var genreIds: List<Int>,

    @ColumnInfo(name = "detail_img")
    @SerializedName("backdrop_path") // 상세 이미지
    var detailImg : String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview") // 영화소개
    var movieOverView : String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path") // 영화 포스터이미지
    var movieMainImg : String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date") // 개봉일
    var releaseDate : String,

    @ColumnInfo(name = "title")
    @SerializedName("title") // 영화제목
    var movieTitle : String,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average") // 평점
    var movieGrade : Double
):Serializable