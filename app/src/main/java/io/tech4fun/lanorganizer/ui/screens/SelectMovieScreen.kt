package io.tech4fun.lanorganizer.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import io.tech4fun.lanorganizer.data.states.MovieUiState
import io.tech4fun.lanorganizer.ui.viewmodels.MovieViewModel

@Composable
fun SelectMovieScreen(modifier: Modifier = Modifier, viewModel: MovieViewModel = hiltViewModel(), onNextButtonClicked : (movies: List<String>) -> Unit){
    val movieList = viewModel.uiState.collectAsState()

    Scaffold(
        content = {
            MovieList(movieList = movieList.value, modifier.padding(it))
        }
    )
}

@Composable
private fun MovieList(movieList: List<MovieUiState>, modifier: Modifier = Modifier) {
    LazyColumn() {
        items(count = movieList.size,
            contentType = {
                it
            }){
            MovieCard(movie = movieList[it])
        }
    }
}

@Composable
private fun MovieCard(movie: MovieUiState, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp)) {
        Row() {
            Text(text = movie.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium)
        }
    }
}