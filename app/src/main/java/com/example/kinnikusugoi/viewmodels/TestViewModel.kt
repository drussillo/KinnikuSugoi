package com.example.kinnikusugoi.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kinnikusugoi.data.AppDatabase
import com.example.kinnikusugoi.data.Units
import com.example.kinnikusugoi.data.user
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {

    var users: String by mutableStateOf<String>("loading...")

    init {
        viewModelScope.launch {
            //db.userDao().insertAll(user("id_010101", "John Smith", Date(), Units.KGS))  // test user

            users = db.userDao().getAll().toString()
        }
    }
}