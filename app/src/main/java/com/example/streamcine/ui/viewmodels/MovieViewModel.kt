package com.example.streamcine.ui.viewmodels

import android.util.Log
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
                for(i in 550..552) {
                    val movie = MoviesApi.retrofitService.getMovies(i)
                    movies.add(movie)
                    Log.e("CO Liste de films : ", movies.toString())
                }
            }
            catch(e : Exception) {
                errorMessage = e.message.toString()
                Log.e("Eroor", errorMessage)
            }
        }
    }
}