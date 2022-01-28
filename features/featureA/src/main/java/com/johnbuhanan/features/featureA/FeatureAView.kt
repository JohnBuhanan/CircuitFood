package com.johnbuhanan.features.featureA

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Preview
@Composable
fun FeatureAView(
    thing: String = "Test string",
    onEvent: (FeatureAEvent) -> Unit = {},
) {
    Timber.e("Composable - FeatureAView")
    Text(text = thing)
}
