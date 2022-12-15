package io.tech4fun.lanorganizer.data.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import io.tech4fun.lanorganizer.CineOnStreamApplication
import io.tech4fun.lanorganizer.data.models.MovieModel
import io.tech4fun.lanorganizer.data.sources.MovieCacheSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface MovieSource {
    suspend fun getMovies(): Flow<List<MovieModel>>
}

interface MovieRepository{
    suspend fun getMovieApps(): Flow<List<MovieModel>>
}

class DefaultMovieRepository @Inject constructor(): MovieRepository {

    private var movieSource : MovieSource

    init {
        val appContext = CineOnStreamApplication.getContext()
        val utilitiesEntryPoint =
            appContext?.let {
                EntryPointAccessors.fromApplication(
                    it, DefaultMovieRepoEntryPoint::class.java)
            }
        movieSource = utilitiesEntryPoint?.movieSource!!

    }

    override suspend fun getMovieApps(): Flow<List<MovieModel>> {
        return movieSource.getMovies()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultMovieRepoEntryPoint {
    var movieSource: MovieSource
}

@InstallIn(SingletonComponent::class)
@Module
object MovieRepositoryModule {
    @Singleton
    @Provides
    fun providMovieRepo(): MovieRepository {
        return DefaultMovieRepository()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object MovieSourceModule {
    @Provides
    @Singleton
    fun provideMovieSource(): MovieSource {
        return MovieCacheSource
    }
}