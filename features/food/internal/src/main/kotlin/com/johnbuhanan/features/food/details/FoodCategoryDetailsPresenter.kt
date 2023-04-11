package com.johnbuhanan.features.food.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.resultlistener.ResultListener
import com.johnbuhanan.common.resultlistener.setResult
import com.johnbuhanan.features.food.FoodCategoryDetailsScreen
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoryAsItemById
import com.johnbuhanan.libraries.food.usecase.GetMealsAsItemsById
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FoodCategoryDetailsPresenter @AssistedInject constructor(
    private val getFoodCategoryById: GetFoodCategoryAsItemById,
    private val getMealsAsItemsById: GetMealsAsItemsById,
    private val resultListener: ResultListener,
    @Assisted private val screen: FoodCategoryDetailsScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<FoodCategoryDetailsState> {
    @Composable
    override fun present(): FoodCategoryDetailsState {
        val category by produceState<FoodItem?>(null, null) {
            getFoodCategoryById(screen.id).fold(
                {
                    value = it
                },
                {

                }
            )
        }
        val foodItems by produceState<List<FoodItem>>(emptyList(), null) {
            getMealsAsItemsById(screen.id).fold(
                {
                    value = it
                },
                {

                }
            )
        }

        return FoodCategoryDetailsState(
            category = category,
            foodItems = foodItems,
        ) { event ->
            when (event) {
                FoodCategoryDetailsEvent.TappedBack -> navigator.pop()
                is FoodCategoryDetailsEvent.TappedFoodItem -> {
                    resultListener.setResult(DetailsResult(event.message))
                    navigator.pop()
                }
            }
        }
    }

    @CircuitInject(FoodCategoryDetailsScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(
            foodCategoryDetailsScreen: FoodCategoryDetailsScreen,
            navigator: Navigator
        ): FoodCategoryDetailsPresenter
    }
}