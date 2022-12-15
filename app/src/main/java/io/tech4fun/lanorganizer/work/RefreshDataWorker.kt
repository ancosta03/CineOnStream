package io.tech4fun.lanorganizer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.tech4fun.lanorganizer.data.sources.MovieCacheSource

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        try{
            MovieCacheSource.getMovies();
        }catch (e: Exception){
            return Result.retry()
        }
        return Result.success()
    }
}