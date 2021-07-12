package com.sauravrp.takehome.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sauravrp.takehome.Work
import com.sauravrp.takehome.db.WorkDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class SampleWorker(
    appContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val work = Work(UUID.randomUUID().toString())
        WorkDb.workDatbase.workDao().insert(work)
         println("doWork created $work")
        return@withContext Result.success()
    }

}