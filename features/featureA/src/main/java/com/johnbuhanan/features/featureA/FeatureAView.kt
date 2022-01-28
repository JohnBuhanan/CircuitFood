package com.johnbuhanan.features.featureA

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.johnbuhanan.common.WithViewModel
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination(
    start = true,
    navGraph = "featureA",
)
@Composable
fun FeatureA() {
    Timber.e("Composable - FeatureA")
    WithViewModel<FeatureAViewModel>(
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

@Composable
fun FeatureAView(
    thing: String,
    onEvent: (FeatureAEvent) -> Unit = {},
) {
    Timber.e("Composable - FeatureAView")
    Text(text = thing)
}
