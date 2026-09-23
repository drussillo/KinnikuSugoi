package com.example.kinnikusugoi.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kinnikusugoi.R
import com.example.kinnikusugoi.viewmodels.TestViewModel
import kotlinx.coroutines.launch


@Composable
fun WorkoutSelect() {
    var showWorkoutEditor by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text="Select Your Workout")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            IconButton(
                modifier = Modifier
                    .scale(1.8f)
                    .padding(vertical = 20.dp),
                onClick = { showWorkoutEditor = true }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_circle_24),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Add a new workout",
                )
            }
        }
    ) { innerPadding ->
        WorkoutList(modifier = Modifier.padding(paddingValues = innerPadding))
        WorkoutEditor(
            show = showWorkoutEditor,
            onDismissRequest = { showWorkoutEditor = false },
            workoutName = "Full Body Workout A"  //TODO!
        )
    }
}


@Composable
private fun WorkoutList(
    viewModel: TestViewModel = hiltViewModel(),  // TODO: temporary test, will replace with Workout ViewModel
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(modifier = modifier, text=viewModel.users) // TODO: temporary test
        }
        items(getSampleWorkouts()) { workout ->
            Text(text = workout)
        }
    }
}


private fun getSampleWorkouts(): List<String> {
    return listOf("Workout A", "Workout B", "Test Workout", "Quick", "Full Body")
}