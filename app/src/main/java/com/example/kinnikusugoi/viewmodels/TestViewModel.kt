package com.example.kinnikusugoi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinnikusugoi.data.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {
    public fun getUsers() {
        viewModelScope.launch {
            val users = db.userDao().getAll().toString();
            println(users)
        }
    }
}