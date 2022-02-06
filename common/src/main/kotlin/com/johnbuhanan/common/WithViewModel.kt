package com.johnbuhanan.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@Suppress("MoveLambdaOutsideParentheses")
@Composable
inline fun <reified VM : BaseViewModel<*, *, *>> Screen.WithViewModel(
    crossinline onEffect: suspend (UiEffect) -> Unit,
    initialize: (VM) -> Unit = {},
    start: (VM, (UiEvent) -> Unit) -> Unit,
) {
    val viewModel: VM = getViewModel()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            Timber.e("WithViewModel - onEffect")
            this.launch { onEffect(effect) }
        }
    }

    initialize(viewModel)

    val onEvent: (UiEvent) -> Unit = { event -> viewModel.setEvent(event) }

    start(
        viewModel,
        onEvent,
    )
}
