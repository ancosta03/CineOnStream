package com.example.streamcine.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.streamcine.ui.viewmodels.MovieViewModel


//val viewModel : MovieViewModel by activityViewModels<MovieViewModel>()

@Composable
fun MovieDetailBody (FilmDirector : String) {
    Column() {
        Text(text = FilmDirector)
    }
}

