package com.johnbuhanan.features.featureA.screen1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.johnbuhanan.features.featureA.screen1.Screen1Event.TappedNext
import timber.log.Timber

@Preview
@Composable
fun FeatureAView(
    message: String = "Test string",
    onEvent: (Screen1Event) -> Unit = {},
) {
    Timber.e("Composable - FeatureAView")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(color = Color.White),
            )
            Button(
                onClick = { onEvent(TappedNext) }
            ) {
                Text("Next")
            }
        }
    }
}
