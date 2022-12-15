package io.tech4fun.lanorganizer.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.tech4fun.lanorganizer.data.repository.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val movieSource: MovieSource
    val movieRepository: MovieRepository
}

class DefaultAppContainer : AppContainer {
    private companion object {
        private const val BASE_URL = "https://60b00d3c1f26610017ffdc23.mockapi.io/api/v1/"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    override val movieSource: MovieSource
        get() = TODO("Not yet implemented")

    override val movieRepository: MovieRepository by lazy {
        DefaultMovieRepository()
    }
}