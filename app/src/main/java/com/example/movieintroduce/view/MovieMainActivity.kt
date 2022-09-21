package com.example.movieintroduce.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieintroduce.R
import com.example.movieintroduce.databinding.ActivityMovieMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MovieMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_main)

        val nav = findViewById<BottomNavigationView>(R.id.nav_bar)
        NavigationUI.setupWithNavController(nav, findNavController(R.id.nav_host))
    }
}