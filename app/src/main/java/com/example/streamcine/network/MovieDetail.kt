package com.example.streamcine.network

import com.squareup.moshi.Json

data class MovieDetail (
    @Json(name = "name") val name : String,
    @Json(name = "film_director") val film_director : String

)