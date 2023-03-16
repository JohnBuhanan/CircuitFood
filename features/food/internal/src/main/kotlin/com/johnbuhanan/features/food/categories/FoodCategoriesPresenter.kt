package com.johnbuhanan.features.food.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.johnbuhanan.features.food.FoodCategoryDetailsScreen
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItems
import com.slack.circuit.Navigator
import com.slack.circuit.Presenter
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class FoodCategoriesPresenter @AssistedInject constructor(
    private val getFoodCategoriesAsItems: GetFoodCategoriesAsItems,
    @Assisted private val navigator: Navigator,
) : Presenter<FoodCategoriesState> {
    @Composable
    override fun present(): FoodCategoriesState {
        var isLoading by remember { mutableStateOf(true) }
        val categories by produceState<List<FoodItem>>(emptyList(), null) {
            isLoading = true
            getFoodCategoriesAsItems().fold(
                {
                    isLoading = false
                    value = it
                },
                {

                }
            )
        }

        return FoodCategoriesState(
            categories = categories,
            isLoading = false
        ) { event ->
            when (event) {
                FoodCategoriesEvent.TappedBack -> navigator.pop()
                is FoodCategoriesEvent.TappedCategory -> navigator.goTo(
                    FoodCategoryDetailsScreen(
                        event.id
                    )
                )
            }
        }
    }

    @CircuitInject(FoodCategoriesScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): FoodCategoriesPresenter
    }
}