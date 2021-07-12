package com.sauravrp.takehome.db

import android.content.Context
import androidx.room.*
import com.sauravrp.takehome.Work
import kotlinx.coroutines.flow.Flow

object WorkDb {
    lateinit var workDatbase: WorkDatabase
    fun init(context: Context) {
        workDatbase = Room
            .databaseBuilder(context,
                WorkDatabase::class.java, "work-db"
            ).build()
    }
}


@Database(
    entities = [Work::class],
    version = 1
)
abstract class WorkDatabase : RoomDatabase() {
    abstract fun workDao(): WorkDao
}

@Dao
interface WorkDao {
    @Insert
    suspend fun insert(vararg work: Work)

    @Query("SELECT * FROM Work")
    fun getAllWork() : Flow<List<Work>>
}

