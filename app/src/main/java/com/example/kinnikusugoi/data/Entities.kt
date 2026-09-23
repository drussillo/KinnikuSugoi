package com.example.kinnikusugoi.data

import androidx.room3.ColumnTypeConverter
import androidx.room3.ColumnTypeConverters
import androidx.room3.Embedded
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import androidx.room3.Relation
import java.util.Date


@Entity
data class user(
    @PrimaryKey val user_id: String,
    val name: String,
    val joined_date: Date,
    val units: Units
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = user::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ],
    indices = [
        Index(
            value = ["user_id", "name"],
            unique = true
        )
    ]
)
data class workout(
    @PrimaryKey val workout_id: String,
    val user_id: String,
    val name: String,
    val last_performed: Date,
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

@Entity(
    indices = [
        Index(
            value = ["user_id", "name"],
            unique = true
        )
    ]
)
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

@Entity(
    primaryKeys = ["workout_id", "exercise_id"],
    indices = [
        Index(
            "workout_id", "exercise_id"
        ),
        Index(
            value = ["exercise_id", "order"],
            unique = true
        )
    ],
)
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
    val start_time: Date,
    val end_time: Date,
    val weight: Float,
    val reps: UInt
)

enum class Units {
    LBS,
    KGS
}


class Converters {
    @ColumnTypeConverter
    fun fromTimestamp(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @ColumnTypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}