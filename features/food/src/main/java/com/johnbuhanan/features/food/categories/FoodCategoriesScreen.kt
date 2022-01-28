package com.johnbuhanan.features.food.categories

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration.Short
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.johnbuhanan.common.WithViewModel
import com.johnbuhanan.features.food.categories.FoodCategoriesEffect.DataWasLoaded
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination(start = true)
@Composable
fun FoodCategoriesScreen() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    WithViewModel<FoodCategoriesViewModel>(
        onEffect = { effect ->
            Timber.e("Composable - onEffect")
            when (effect) {
                is DataWasLoaded -> {
                    Timber.e("Composable - onEffect - DataWasLoaded")
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Food categories are loaded.",
                        duration = Short
                    )
                }
            }
        },
        start = { viewModel, onEvent ->
            Timber.e("FoodCategoriesView - start")
            val state = viewModel.state.value
            FoodCategoriesView(state.categories, state.isLoading, onEvent)
        }
    )
}
