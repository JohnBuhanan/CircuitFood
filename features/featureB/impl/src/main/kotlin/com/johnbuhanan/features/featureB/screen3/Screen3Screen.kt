package com.johnbuhanan.features.featureB.screen3

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.viewmodel.WithViewModel
import com.johnbuhanan.features.featureB.screen3.Screen3Effect.ShowToast
import timber.log.Timber

class Screen3Screen : AndroidScreen() {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        Timber.e("Composable - FeatureB")
        WithViewModel<Screen3ViewModel>(
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
                    is Screen3State -> Screen3View(
                        message = state.message,
                        onEvent = onEvent
                    )
                }
            },
        )
    }
}

