package com.example.streamcine.data.models

import com.squareup.moshi.Json

data class MovieModel (
    @Json(name = "name") val name : String,
    @Json(name = "film_director") val film_director : String,
    @Json(name = "imageUrl") val imageUrl : String,
    @Json(name = "desc") val desc : String,
    @Json(name = "category") val category : String
)