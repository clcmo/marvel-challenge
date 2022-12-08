package com.clcmo.marvelchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clcmo.marvelchallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MarvelCharacters)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}