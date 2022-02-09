package com.johnbuhanan.common.viewmodel

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import timber.log.Timber

@Suppress("MoveLambdaOutsideParentheses")
@Composable
inline fun <reified VM : BaseViewModel<*, *, *>> Screen.WithViewModel(
    crossinline onEffect: suspend (UiEffect) -> Unit,
    initialize: (VM) -> Unit = {},
    start: (VM, (UiEvent) -> Unit) -> Unit,
) {
    val viewModel: VM = getViewModel()

    viewModel.collectEffect { effect ->
        Timber.e("WithViewModel - onEffectOuter")
        onEffect(effect)
    }

    initialize(viewModel)

    val onEvent: (UiEvent) -> Unit = { event -> viewModel.setEvent(event) }

    start(
        viewModel,
        onEvent,
    )
}
