package com.example.movieintroduce.domain.repository

import com.example.movieintroduce.data.model.NowMoviesResponse
import com.example.movieintroduce.data.util.Resource

interface MovieRepository {
    suspend fun getIntroduceMovie(apiKey : String, country : String) : Resource<NowMoviesResponse>
}