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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kinnikusugoi.R
import com.example.kinnikusugoi.viewmodels.TestViewModel
import com.example.kinnikusugoi.viewmodels.WorkoutViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


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
                Text(
                    fontWeight = FontWeight.Bold,
                    text="Select Your Workout"
                )
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
    viewModel: WorkoutViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    if(viewModel.workoutsDisplay.isEmpty()) {
        Text(
            modifier = modifier,  // TODO: Make it pretty
            text = "You have no workouts. Press the + button to add one."
        )
        return
    }

    LazyColumn(
        modifier = modifier
    ) {
        val dateFormatter = SimpleDateFormat("E, dd/MM/YY")
        items(viewModel.workoutsDisplay) { workout ->
            /*
            val annotatedWorkoutText = buildAnnotatedString {
                withStyle() {
                    append("test")
                }
            }
            Text(text = annotatedWorkoutText)
            */
            Text(text = "${workout.first}, last performed on ${dateFormatter.format(workout.second)}")
        }
    }
}
