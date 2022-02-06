package com.johnbuhanan.features.food.details

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.WithViewModel
import timber.log.Timber

data class FoodCategoryDetailsScreen(val categoryId: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        Timber.e("Composable - FoodCategoryDetails")
        WithViewModel<FoodCategoryDetailsViewModel>(
            onEffect = { effect ->
                when (effect) {
                    is FoodCategoryDetailsEffect -> {
                        val scaffoldState: ScaffoldState = rememberScaffoldState()
                        scaffoldState.snackbarHostState.showSnackbar("blah")
                    }
                }
            },
            initialize = { viewModel ->
                viewModel.initialize(categoryId)
            },
            start = { viewModel, onEvent ->
                when (val state = viewModel.state.value) {
                    is FoodCategoryDetailsState -> FoodCategoryDetailsView(
                        category = state.category,
                        categoryFoodItems = state.categoryFoodItems,
                        onEvent = onEvent
                    )
                }
            },
        )
    }
}
