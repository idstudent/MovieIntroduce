package com.example.movieintroduce.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieintroduce.BuildConfig
import com.example.movieintroduce.api.ApiManager
import com.google.gson.Gson
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class MovieIntroduceRepository {
    private var mutableMovieIntroduceLiveData : MutableLiveData<List<MovieInfo>> = MutableLiveData()
    private var movieIntroduceList = ArrayList<NowMoviesResponse>()

    fun getNowMovies() : MutableLiveData<List<MovieInfo>>{
        movieIntroduceList.clear()

        ApiManager.getInstance().getNowViewMovies(BuildConfig.api_key, "ko")
                .enqueue(object : retrofit2.Callback<NowMoviesResponse> {
                    override fun onFailure(call: Call<NowMoviesResponse>, t: Throwable) {
                        Log.e("tag", "fail" + t.message)
                    }

                    override fun onResponse(call: Call<NowMoviesResponse>, response: Response<NowMoviesResponse>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val response  = response.body()

                                if(response?.movieInfoList != null) {
                                    val movies = response.movieInfoList
                                    mutableMovieIntroduceLiveData.value = movies
                                }
                            }
                        }
                    }
                })
        return mutableMovieIntroduceLiveData
    }
}