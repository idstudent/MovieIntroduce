package com.example.movieintroduce.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NowMoviesResponse
import com.example.domain.util.Resource
import com.example.domain.usecase.GetIntroduceMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieIntroduceViewModel @Inject constructor(
    private val application : Application,
    private val getIntroduceMovieUseCase: GetIntroduceMovieUseCase
) : ViewModel() {
    private var _introduceMovieList : MutableLiveData<Resource<NowMoviesResponse>> = MutableLiveData()
    val introduceMovieList : LiveData<Resource<NowMoviesResponse>> get() = _introduceMovieList

    fun getIntroduceMovies(apiKey : String, country : String): Job {
        return viewModelScope.launch {
            try{
                if(isNetworkAvailable(application)) {
                    val response = getIntroduceMovieUseCase.execute(apiKey, country)
                    _introduceMovieList.postValue(response)
                }else {
                    _introduceMovieList.postValue(Resource.Error(("인터넷 연결을 확인해주세요.")))
                }
            }catch (e : Exception) {
                _introduceMovieList.postValue(Resource.Error((e.message.toString())))
            }
        }
    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}