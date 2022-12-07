package com.example.streamcine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

import com.example.streamcine.ui.theme.StreamCineTheme
import com.example.streamcine.ui.viewmodels.MovieViewModel
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview
import com.example.streamcine.data.models.MovieModel
import com.example.streamcine.ui.screens.AppScreen
import com.example.streamcine.ui.screens.MovieItem

class MainActivity : ComponentActivity() {

    private val movieViewModel by viewModels<MovieViewModel>()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamCineTheme {
                val windowSize = calculateWindowSizeClass(activity = this)
                AppScreen(windowsSize = windowSize)
                Surface(color = MaterialTheme.colors.background) {
                    //MovieList(movieList = movieViewModel.movies)
                    DefaultP()
                    movieViewModel.getMovies()
                }
            }
        }
    }
}

@Composable
fun MovieList(movieList: List<MovieModel>) {
    LazyColumn {
        itemsIndexed(items = movieList) {index, item ->
            MovieItem(movie = item)
        }
    }
}

@Composable
fun DefaultP() {
    StreamCineTheme {
        val movie = MovieModel("Developer's Says", "Hi Guys !", "", "Hello", "Action")
        MovieItem(movie = movie)
    }
}