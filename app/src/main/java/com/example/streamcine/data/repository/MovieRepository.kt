package com.example.streamcine.data.repository

import com.example.streamcine.network.MoviesApiService
import com.example.streamcine.data.models.MovieModel

class MovieRepositoryt(private val MovieApiService : MoviesApiService) {
    suspend fun getMovies(movie : MovieModel) = MovieApiService.getMovies()
    suspend fun getMovieDetail(movie : MovieModel) = MovieApiService.getMovieDetails()
    suspend fun getMovieTrailer(movie : MovieModel) = MovieApiService.getMovieTrailer()
}