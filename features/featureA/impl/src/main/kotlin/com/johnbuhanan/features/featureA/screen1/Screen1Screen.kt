package com.johnbuhanan.features.featureA.screen1

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.WithViewModel
import timber.log.Timber

class Screen1Screen : AndroidScreen() {
    @Composable
    override fun Content() {
        Timber.e("Composable - FeatureA")
        WithViewModel<Screen1ViewModel>(
            onEffect = { effect ->
                when (effect) {
                    is FeatureAEffect -> {
                        val scaffoldState: ScaffoldState = rememberScaffoldState()
                        scaffoldState.snackbarHostState.showSnackbar("blah")
                    }
                }
            },
            initialize = { viewModel ->
                viewModel.initialize("categoryId")
            },
            start = { viewModel, onEvent ->
                when (val state = viewModel.state.value) {
                    is FeatureAState -> FeatureAView(
                        thing = state.thing,
                        onEvent = onEvent
                    )
                }
            },
        )
    }
}

