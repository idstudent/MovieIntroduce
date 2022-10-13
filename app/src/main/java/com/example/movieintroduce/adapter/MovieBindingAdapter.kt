package com.example.movieintroduce.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object MovieBindingAdapter {
    @BindingAdapter("app:movieImg")
    @JvmStatic
    fun showImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context).load("https://image.tmdb.org/t/p/w500" + it)
                .into(imageView)
        }
    }
}