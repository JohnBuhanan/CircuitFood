package com.johnbuhanan.features.featureA.screen2

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.WithViewModel
import com.johnbuhanan.features.featureA.screen1.Screen1State
import com.johnbuhanan.features.featureA.screen1.Screen1View
import com.johnbuhanan.features.featureA.screen2.Screen2Effect.ShowToast
import timber.log.Timber

class Screen2Screen : AndroidScreen() {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        Timber.e("Composable - FeatureA")
        WithViewModel<Screen2ViewModel>(
            onEffect = { effect ->
                when (effect) {
                    is ShowToast -> {
                        Timber.e("Composable - onEffect - ShowToast")
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            start = { viewModel, onEvent ->
                when (val state = viewModel.state.value) {
                    is Screen1State -> Screen1View(
                        message = state.message,
                        onEvent = onEvent
                    )
                }
            },
        )
    }
}

