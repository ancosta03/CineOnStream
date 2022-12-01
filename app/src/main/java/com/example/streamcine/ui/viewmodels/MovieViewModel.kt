package com.example.streamcine.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamcine.data.models.MovieModel
import com.example.streamcine.network.MoviesApi
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    var movies : MutableList<MovieModel> = mutableListOf()
    var errorMessage : String by mutableStateOf("")

    public fun getMovies() {
        viewModelScope.launch {
            try {
                val movieList = MoviesApi.retrofitService.getMovies() as MutableList<MovieModel>
                movies = movieList
            }
            catch(e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}