package com.example.data.model


import com.example.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class NowMoviesResponse (
        var page : Int,
        @SerializedName("results")
        var movieInfoList : ArrayList<MovieData>
) : BaseResponse<List<Movie>>() {
    override fun mapper(): List<Movie> {
        val result = mutableListOf<Movie>()

        movieInfoList.map {
            result.add(Movie(
                it.movieId,
                it.detailImg,
                it.movieOverView,
                it.movieMainImg,
                it.releaseDate,
                it.movieTitle,
                it.movieGrade
            ))
        }
        return result
    }

}