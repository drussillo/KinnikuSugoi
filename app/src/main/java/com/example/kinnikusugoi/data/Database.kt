package com.example.kinnikusugoi.data

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.room3.Database
import androidx.room3.Embedded
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import androidx.room3.Relation
import androidx.room3.Room
import androidx.room3.RoomDatabase


@Database(
    entities =
        [
            user::class,
            workout::class,
            exercise::class,
            workout_exercise::class,
            //history::class
        ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
}


@Entity
data class user(
    @PrimaryKey val user_id: String,
    val name: String,
    val joined_date: Long,  // TODO
    val units: Units
)

@Entity
data class workout(
    @PrimaryKey val workout_id: String,
    val user_id: String,
    val name: String,
    val last_performed: Long,  // TODO
)

@Entity
data class user_with_workout(
    @Embedded val user: user,
    @Relation(
        parentColumns = ["user_id"],
        entityColumns = ["user_id"]
    )
    val workouts: List<workout>
)

@Entity
data class exercise(
    @PrimaryKey val exercise_id: String,
    val user_id: String,
    val name: String
)

@Entity
data class user_with_exercise(
    @Embedded val user: user,
    @Relation(
        parentColumns = ["user_id"],
        entityColumns = ["user_id"]
    )
    val exercises: List<exercise>
)

@Entity(primaryKeys = ["workout_id", "exercise_id"], indices = [Index("workout_id", "exercise_id")])
data class workout_exercise(
    val workout_id: String,
    val exercise_id: String,
    val order: UInt
)


@Entity
data class history(
    @PrimaryKey val history_id: String,
    val exercise_id: String,
    val instance: UInt,
    val start_time: Long,  // TODO
    val end_time: Long,  // TODO
    val weight: Float,
    val reps: UInt
)

enum class Units {
    LBS,
    KGS
}



// test
@Composable
fun testdb() {
    val applicationContext = LocalContext.current

    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "app.db"
    ).build()

    LaunchedEffect(Unit) {
        //db.userDao().insertAll(user("uid001122", "davide Russillo", 223, Units.KGS))
        val users = db.userDao().getAll()

        Log.d("Test1_DB", "Users: $users")
    }
}
