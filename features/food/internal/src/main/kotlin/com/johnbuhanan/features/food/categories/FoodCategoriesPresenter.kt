package com.johnbuhanan.features.food.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.resultlistener.ResultListener
import com.johnbuhanan.common.resultlistener.onResult
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.johnbuhanan.features.food.FoodCategoryDetailsScreen
import com.johnbuhanan.features.food.details.DetailsResult
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
    private val resultListener: ResultListener,
    @Assisted private val navigator: Navigator,
) : Presenter<FoodCategoriesState> {
    @Composable
    override fun present(): FoodCategoriesState {
        var isLoading by rememberSaveable { mutableStateOf(true) }
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

        val resultText by rememberSaveable { resultListener.onResult<DetailsResult?>(null) }

        return FoodCategoriesState(
            categories = categories,
            isLoading = isLoading,
            resultText = resultText?.value ?: "",
        ) { event ->
            when (event) {
                is FoodCategoriesEvent.TappedCategory -> {
                    navigator.goTo(FoodCategoryDetailsScreen(event.id))
                }

                is FoodCategoriesEvent.TappedBack -> {
                    navigator.pop()
                }
            }
        }
    }

    @CircuitInject(FoodCategoriesScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): FoodCategoriesPresenter
    }
}