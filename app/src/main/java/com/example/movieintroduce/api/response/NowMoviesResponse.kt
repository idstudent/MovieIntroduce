package com.example.movieintroduce.api.response

import com.google.gson.annotations.SerializedName


/*
장르
{
   "genres":[
      {
         "id":28,
         "name":"Action"
      },
      {
         "id":12,
         "name":"Adventure"
      },
      {
         "id":16,
         "name":"Animation"
      },
      {
         "id":35,
         "name":"Comedy"
      },
      {
         "id":80,
         "name":"Crime"
      },
      {
         "id":99,
         "name":"Documentary"
      },
      {
         "id":18,
         "name":"Drama"
      },
      {
         "id":10751,
         "name":"Family"
      },
      {
         "id":14,
         "name":"Fantasy"
      },
      {
         "id":36,
         "name":"History"
      },
      {
         "id":27,
         "name":"Horror"
      },
      {
         "id":10402,
         "name":"Music"
      },
      {
         "id":9648,
         "name":"Mystery"
      },
      {
         "id":10749,
         "name":"Romance"
      },
      {
         "id":878,
         "name":"Science Fiction"
      },
      {
         "id":10770,
         "name":"TV Movie"
      },
      {
         "id":53,
         "name":"Thriller"
      },
      {
         "id":10752,
         "name":"War"
      },
      {
         "id":37,
         "name":"Western"
      }
   ]
}
 */
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
)