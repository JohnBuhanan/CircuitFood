package com.johnbuhanan.features.${featurename}.view

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.johnbuhanan.features.login.viewmodel.${ScreenName}Event

@Preview
@Composable
fun ${ScreenName}View(
    isLoading: Boolean = false,
    onEvent: (${ScreenName}Event) -> Unit = {},
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CategoriesAppBar()
        },
    ) {
        Box {

            if (isLoading)
                LoadingBar()
        }
    }
}
