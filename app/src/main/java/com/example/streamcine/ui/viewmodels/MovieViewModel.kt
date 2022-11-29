package com.example.streamcine.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamcine.network.MovieDetail
import com.example.streamcine.network.MoviesApi
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    var movies : MutableList<MovieDetail> = mutableListOf()

    public fun getMovies() {
        viewModelScope.launch {
            try {
                movies = MoviesApi.retrofitService.getMovies() as MutableList<MovieDetail>
            }
            catch(e : Exception) {

            }
        }
    }
}