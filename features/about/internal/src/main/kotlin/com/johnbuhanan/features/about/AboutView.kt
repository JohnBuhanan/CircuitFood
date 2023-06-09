package com.johnbuhanan.features.about

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.ui.AppBar
import com.slack.circuit.codegen.annotations.CircuitInject

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(AboutScreen::class, AppScope::class)
@Composable
fun FoodCategoriesView(
    aboutState: AboutState,
    modifier: Modifier,
) {
    BackHandler {
        aboutState.eventSink(AboutEvent.TappedBack)
    }
    Scaffold(
        topBar = {
            AppBar()
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text(aboutState.aboutText)
        }
    }
}
