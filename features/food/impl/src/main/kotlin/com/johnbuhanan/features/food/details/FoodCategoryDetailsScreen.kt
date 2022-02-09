package com.johnbuhanan.features.food.details

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.viewmodel.WithViewModel
import timber.log.Timber

data class FoodCategoryDetailsScreen(val categoryId: String) : AndroidScreen() {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        Timber.e("Composable - FoodCategoryDetails")
        WithViewModel<FoodCategoryDetailsViewModel>(
            onEffect = { effect ->
                Timber.e("FoodCategoryDetailsEffect")
                when (effect) {
                    is FoodCategoryDetailsEffect.ShowToast -> {
                        Timber.e("FoodCategoryDetailsEffect.ShowSnackbar")
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            initialize = { viewModel ->
                viewModel.initialize(categoryId)
            },
            start = { viewModel, onEvent ->
                when (val state = viewModel.state.collectAsState().value) {
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
