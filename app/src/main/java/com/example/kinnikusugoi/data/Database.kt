package com.example.kinnikusugoi.data

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.room3.Dao
import androidx.room3.Database
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.Insert
import androidx.room3.PrimaryKey
import androidx.room3.Query
import androidx.room3.Relation
import androidx.room3.Room
import androidx.room3.RoomDatabase
import dagger.hilt.android.lifecycle.HiltViewModel


@Database(entities = [user::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
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
    @Relation(
        parentColumns = ["user_id"],
        entityColumns = ["user_id"]
    )
    val name: String,
    val last_performed: Long,  // TODO
)

@Entity
data class exercise(
    @PrimaryKey val exercise_id: String,
    @Relation(
        parentColumns = ["user_id"],
        entityColumns = ["user_id"]
    )
    val name: String
)

@Entity(primaryKeys = ["workout_id", "exercise_id"], indices = [Index("workout_id", "exercise_id")])
data class workout_exercise(
    val order: UInt
)

enum class Units {
    LBS,
    KGS
}


@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(vararg users: user)

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<user>
}


@Composable
fun testdb() {
    val applicationContext = LocalContext.current

    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "app.db"
    ).build()

    LaunchedEffect(Unit) {
        db.userDao().insertAll(user("uid001122", "davide Russillo", 223, Units.KGS))
        val users = db.userDao().getAll()

        Log.d("Test1_DB", "Users: $users")
    }
}
