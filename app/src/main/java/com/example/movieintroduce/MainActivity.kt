package com.example.movieintroduce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.movieintroduce.api.ApiManager
import com.example.movieintroduce.databinding.ActivityMainBinding
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ApiManager.getInstance().getNowViewMovies(BuildConfig.api_key)
            .enqueue(object : retrofit2.Callback<JsonElement> {
                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    Log.e("tag", "fail" + t.message)
                }

                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                        }
                    }
                }
            })
    }
}