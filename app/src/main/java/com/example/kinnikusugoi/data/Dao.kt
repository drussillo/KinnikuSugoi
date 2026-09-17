package com.example.kinnikusugoi.data

import android.content.Context
import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(vararg users: user)

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<user>
}

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insertAll(vararg workouts: workout)

    @Query("SELECT * FROM workout")
    suspend fun getAll(): List<workout>
}

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insertAll(vararg exercises: exercise)

    @Query("SELECT * FROM exercise")
    suspend fun getAll(): List<exercise>
}

@Dao
interface WorkoutExerciseDao {
    @Insert
    suspend fun insertAll(vararg workout_exercise: workout_exercise)

    @Query("SELECT * FROM workout_exercise")
    suspend fun getAll(): List<workout_exercise>
}

@Dao
interface HistoryDao {
    @Insert
    suspend fun insertAll(vararg history: history)

    @Query("SELECT * FROM history")
    suspend fun getAll(): List<history>
}


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "KinnikuSugoi.db"
        ).build()
    }
}
