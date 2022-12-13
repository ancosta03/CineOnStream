package com.example.streamcine.data.models

import com.squareup.moshi.Json

data class MovieModel (
    @Json(name = "original_title") val original_title : String,
    @Json(name = "overview") val overview : String,
    @Json(name = "popularity") val popularity : String,
    @Json(name = "vote_average") val vote_average : String,
    @Json(name = "vote_count") val vote_count : String,
    @Json(name = "poster_path") val poster_path : String
)