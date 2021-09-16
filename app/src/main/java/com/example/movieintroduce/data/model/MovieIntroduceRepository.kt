package com.example.movieintroduce.data.model

import androidx.lifecycle.MutableLiveData

class MovieIntroduceRepository {
    private var mutableMovieIntroduceLiveData : MutableLiveData<List<Movie>> = MutableLiveData()
    private var movieIntroduceList = ArrayList<NowMoviesResponse>()

//    fun getNowMovies() : MutableLiveData<List<Movie>>{
//        movieIntroduceList.clear()
//
//        ApiManager.getInstance().getNowViewMovies(BuildConfig.api_key, "ko")
//                .enqueue(object : retrofit2.Callback<NowMoviesResponse> {
//                    override fun onFailure(call: Call<NowMoviesResponse>, t: Throwable) {
//                        Log.e("tag", "fail" + t.message)
//                    }
//
//                    override fun onResponse(call: Call<NowMoviesResponse>, response: Response<NowMoviesResponse>) {
//                        if (response.isSuccessful) {
//                            response.body()?.let {
//                                val response  = response.body()
//
//                                if(response?.movieInfoList != null) {
//                                    val movies = response.movieInfoList
//                                    mutableMovieIntroduceLiveData.value = movies
//                                }
//                            }
//                        }
//                    }
//                })
//        return mutableMovieIntroduceLiveData
//    }
}