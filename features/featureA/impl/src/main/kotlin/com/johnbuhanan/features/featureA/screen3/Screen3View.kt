package com.johnbuhanan.features.featureA.screen3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johnbuhanan.features.featureA.screen3.Screen3Event.TappedNext
import timber.log.Timber

@Preview
@Composable
fun Screen3View(
    message: String = "Test string",
    onEvent: (Screen3Event) -> Unit = {},
) {
    Timber.e("Composable - FeatureAView")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(300.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                color = Color.Black,
            )
            Button(
                onClick = { onEvent(TappedNext) },
            ) {
                Text("Next")
            }
        }
    }
}
