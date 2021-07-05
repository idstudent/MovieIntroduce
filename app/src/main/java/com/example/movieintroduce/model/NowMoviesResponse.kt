package com.example.movieintroduce.model

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NowMoviesResponse (
    @SerializedName("page")
    var page : Int,
    @SerializedName("results")
    var movieInfoList : ArrayList<MovieInfo>
)
data class MovieInfo(
    @SerializedName("id")
    var id : Int,
    @SerializedName("backdrop_path") // 상세 이미지
    var detailImg : String,
    @SerializedName("genre_ids") // 장르
    var genre : ArrayList<Int>,
    @SerializedName("overview") // 영화소개
    var movieOverView : String,
    @SerializedName("poster_path") // 영화 포스터이미지
    var movieMainImg : String,
    @SerializedName("release_date") // 개봉일
    var releaseDate : String,
    @SerializedName("title") // 영화제목
    var movieTitle : String,
    @SerializedName("vote_average") // 평점
    var movieGrade : Double
):Serializable