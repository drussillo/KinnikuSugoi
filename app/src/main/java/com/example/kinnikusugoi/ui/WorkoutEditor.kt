package com.example.kinnikusugoi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.kinnikusugoi.viewmodels.TestViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutEditor(
    //viewModel: TestViewModel = hiltViewModel(),  // TODO
    show: Boolean,
    onDismissRequest: () -> Unit,
    workoutName: String
) {
    if(show) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onDismissRequest = onDismissRequest,
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    Text(
                        text = workoutName,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.padding(paddingValues = innerPadding),
                ) {
                    Text(text = "exercises here")
                }
            }
        }
    }
}
