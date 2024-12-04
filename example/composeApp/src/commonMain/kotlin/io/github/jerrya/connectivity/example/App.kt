package io.github.jerrya.connectivity.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jerrya.connectivity.getConnectivity
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    viewModel: AppViewModel = AppViewModel(),
) {
    val connectivity = getConnectivity()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MaterialTheme {
        AppContent(uiState)
    }
}

@Composable
fun AppContent(
    uiState: UiState,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Online = ${uiState.isOnline}")
    }
}