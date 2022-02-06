package com.johnbuhanan.features.food.categories

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration.Short
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.johnbuhanan.common.WithViewModel
import com.johnbuhanan.features.food.categories.FoodCategoriesEffect.ShowSnackbar
import timber.log.Timber

class FoodCategoriesScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
//        val postListScreen = rememberScreen(SharedScreen.PostList)
//        val postDetailsScreen = rememberScreen(SharedScreen.PostDetails(id = postId))

        // navigator.push(postListScreen)
        // navigator.push(postDetailsScreen)
        val scaffoldState: ScaffoldState = rememberScaffoldState()

        WithViewModel<FoodCategoriesViewModel>(
            onEffect = { effect ->
                Timber.e("Composable - onEffect")
                when (effect) {
                    is ShowSnackbar -> {
                        Timber.e("Composable - onEffect - DataWasLoaded")
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = effect.message,
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
}
