package com.johnbuhanan.features.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.johnbuhanan.common.di.AppScope
import com.slack.circuit.Presenter
import com.slack.circuit.codegen.annotations.CircuitInject
import javax.inject.Inject

@CircuitInject(AboutScreen::class, AppScope::class)
class AboutPresenter @Inject constructor() : Presenter<AboutState> {
    @Composable
    override fun present(): AboutState {
        var isLoading by rememberSaveable { mutableStateOf(true) }


        val aboutText by rememberSaveable {
            mutableStateOf("This is the ABOUT page.")
        }

        return AboutState(
            aboutText = aboutText,
            isLoading = isLoading,
        )
    }
}