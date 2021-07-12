package com.sauravrp.takehome

import android.app.Application
import androidx.work.*
import com.sauravrp.takehome.db.WorkDb
import com.sauravrp.takehome.work.SampleWorker
import java.util.concurrent.TimeUnit

class WorkApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        WorkDb.init(this)
        startWork()
    }

    private fun startWork() {
        val workRequest: WorkRequest =
            PeriodicWorkRequestBuilder<SampleWorker>(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS,
                PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS, TimeUnit.MILLISECONDS)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }
}