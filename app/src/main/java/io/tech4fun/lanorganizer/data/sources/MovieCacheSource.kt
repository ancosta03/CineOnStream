package io.tech4fun.lanorganizer.data.sources

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import io.tech4fun.lanorganizer.CineOnStreamApplication
import io.tech4fun.lanorganizer.data.AppDatabase
import io.tech4fun.lanorganizer.data.models.MovieModel
import io.tech4fun.lanorganizer.data.repository.MovieSource
import io.tech4fun.lanorganizer.work.RefreshDataWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.concurrent.TimeUnit


@Dao
interface MovieDao{
    @Query("SELECT * FROM games")
    fun getAll(): Flow<List<MovieModel>>

    @Query("SELECT * FROM games WHERE name = :gameName")
    fun getByName(gameName: String): Flow<MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: List<MovieModel>)
}

object MovieCacheSource : MovieSource {

    private lateinit var appDatabase : AppDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    private var lastUpdateTime: Instant = Instant.now()

    init {
        val appContext = CineOnStreamApplication.getContext()
        val utilitiesEntryPoint =
            appContext?.let {
                EntryPointAccessors.fromApplication(
                    it, DefaultCacheMovieSourceEntryPoint::class.java)
            }
        appDatabase = utilitiesEntryPoint?.appDatabase!!

        //Launch a periodic work to update the cache
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.HOURS).build()
        WorkManager.getInstance(CineOnStreamApplication.getContext()!!).enqueue(repeatingRequest);
    }

    suspend fun refreshMovies(){
        withContext(Dispatchers.IO){
            val movies = MovieOnlineSource.getMovies()
            appDatabase.movieDao().insert(movies)
        }
    }

    override suspend fun getMovies(): Flow<List<MovieModel>> {
        //refreshMovies()
        return appDatabase.movieDao().getAll()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultCacheMovieSourceEntryPoint {
    var appDatabase: AppDatabase
}