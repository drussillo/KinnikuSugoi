package com.example.kinnikusugoi

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun WorkoutSelect(
    onNavigateToWorkoutEditor: () -> Unit
) {
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
                onClick = onNavigateToWorkoutEditor
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
    }
}


@Composable
private fun WorkoutList(modifier: Modifier = Modifier) {
    Text(modifier=modifier, text = "workout1..etc")
}