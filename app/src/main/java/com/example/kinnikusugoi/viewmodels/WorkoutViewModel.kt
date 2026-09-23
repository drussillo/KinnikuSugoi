package com.example.kinnikusugoi.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinnikusugoi.data.AppDatabase
import com.example.kinnikusugoi.data.workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {
    init {
        viewModelScope.launch {
            /*
            db.workoutDao().insertAll(
                workout(
                    workout_id = "wid_00001",
                    user_id = "nouser",
                    name = "WorkoutA",
                    last_performed = Date()
                )
            )
            */

            workouts = db.workoutDao().getNames()
        }
    }

    var workouts by mutableStateOf<List<Pair<String, String>>>(emptyList<Pair<String, String>>())
}