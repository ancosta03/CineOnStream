package com.example.streamcine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

import com.example.streamcine.ui.theme.StreamCineTheme
import com.example.streamcine.ui.viewmodels.MovieViewModel
import androidx.compose.material.Text

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MovieViewModel>()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamCineTheme {
                val windowSize = calculateWindowSizeClass(this)
                //AppScreen(windowSize)
                viewModel.getMovies()
                //MoviesDisplay(listMovies = listmovies)
                Text(text = viewModel.movies.toString())
                Log.e("MyString", viewModel.movies.toString())
            }
        }
    }
}

//@Composable
//fun MoviesDisplay(listMovies: Unit) {
//    for (movies in listMovies) {
//        Text(text = movies.toString())
//    }
//}
