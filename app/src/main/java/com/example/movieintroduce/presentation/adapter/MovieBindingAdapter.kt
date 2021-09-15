package com.example.movieintroduce.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object MovieBindingAdapter {
    @BindingAdapter("app:mainImg")
    @JvmStatic
    fun showImage(imageView: ImageView, url: String?) {
    url?.let{
            Glide.with(imageView.context).load("https://image.tmdb.org/t/p/w500"+it).into(imageView)
        }
    }
    @BindingAdapter("app:detailImg")
    @JvmStatic
    fun showDetailImage(imageView: ImageView, url: String?) {
        url?.let{
            Glide.with(imageView.context).load("https://image.tmdb.org/t/p/w500"+it).into(imageView)
        }
    }
}