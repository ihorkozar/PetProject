package com.example.petproject.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.petproject.db.getDatabase
import com.example.petproject.repository.RedditRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = RedditRepository(database)
        return try {
            repository.refreshChildren()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}