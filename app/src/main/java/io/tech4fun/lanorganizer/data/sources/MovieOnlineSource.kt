package io.tech4fun.lanorganizer.data.sources

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.tech4fun.lanorganizer.data.models.MovieModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object MovieOnlineSource {
    private const val BASE_URL = "https://60b00d3c1f26610017ffdc23.mockapi.io/api/v1/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    interface MovieAppsService{
        @GET("games")
        suspend fun getAppList() : List<MovieModel>
    }

    private val retrofitService: MovieAppsService by lazy {
        retrofit.create(MovieAppsService::class.java)
    }

    suspend fun getMovies(): List<MovieModel> {
        return retrofitService.getAppList()
    }
}