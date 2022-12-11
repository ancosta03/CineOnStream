package com.example.streamcine.data.repository

import com.example.streamcine.network.MoviesApiService
import com.example.streamcine.data.models.MovieModel

class MovieRepository(private val MovieApiService : MoviesApiService) {
    suspend fun getMovies() = MovieApiService.getMovies()
    //suspend fun getMovieDetail(movie : MovieModel) = MovieApiService.getMovieDetails()
    //suspend fun getMovieTrailer(movie : MovieModel) = MovieApiService.getMovieTrailer()
}