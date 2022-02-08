package com.johnbuhanan.features.featureA.screen1

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.WithViewModel
import com.johnbuhanan.features.featureA.screen1.Screen1Effect.ShowToast
import timber.log.Timber

class Screen1Screen : AndroidScreen() {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        Timber.e("Composable - FeatureA")
        WithViewModel<Screen1ViewModel>(
            onEffect = { effect ->
                when (effect) {
                    is ShowToast -> {
                        Timber.e("Composable - onEffect - ShowToast")
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            initialize = { viewModel ->
                viewModel.initialize("categoryId")
            },
            start = { viewModel, onEvent ->
                when (val state = viewModel.state.value) {
                    is Screen1State -> FeatureAView(
                        thing = state.thing,
                        onEvent = onEvent
                    )
                }
            },
        )
    }
}

