package com.johnbuhanan.features.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.johnbuhanan.common.di.AppScope
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AboutPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<AboutState> {
    @Composable
    override fun present(): AboutState {
        val aboutText by rememberSaveable {
            mutableStateOf("This is the ABOUT page.")
        }

        return AboutState(aboutText) {
            when (it) {
                AboutEvent.TappedBack -> {
                    navigator.pop()
                }
            }
        }
    }

    @CircuitInject(AboutScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): AboutPresenter
    }
}