package com.johnbuhanan.features.food.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.resultlistener.ResultListener
import com.johnbuhanan.common.resultlistener.onResult
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.johnbuhanan.features.food.FoodCategoryDetailsScreen
import com.johnbuhanan.features.food.details.DetailsResult
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItems
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
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
        val resultText by rememberSaveable { resultListener.onResult<DetailsResult?>(null) }

        val state by produceState<FoodCategoriesState>(FoodCategoriesState.Loading) {
            getFoodCategoriesAsItems().fold(
                {
                    value = FoodCategoriesState.Success(
                        categories = it,
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
                },
                {

                }
            )
        }

        return state
    }

    @CircuitInject(FoodCategoriesScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): FoodCategoriesPresenter
    }
}