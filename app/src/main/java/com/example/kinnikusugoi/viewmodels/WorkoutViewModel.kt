package com.example.kinnikusugoi.viewmodels

import androidx.lifecycle.ViewModel
import com.example.kinnikusugoi.data.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {
}