package com.example.kinnikusugoi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.kinnikusugoi.data.testdb


@Composable
fun StartScreen(
    onNavigateToWorkoutSelect: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Logo()
        }
    ) { innerPadding ->
        MainContents(
            modifier = Modifier.padding(paddingValues = innerPadding),
            onNavigateToWorkoutSelect = onNavigateToWorkoutSelect,
        )
    }
}

@Composable
private fun Logo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "LOGO")
    }
}

@Composable
private fun MainContents(modifier: Modifier = Modifier, onNavigateToWorkoutSelect: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = Modifier.scale(1.5f),
            shape = RoundedCornerShape(6.dp),
            onClick = onNavigateToWorkoutSelect,
        ) {
            Text(text = "New Workout")
        }
    }
}

