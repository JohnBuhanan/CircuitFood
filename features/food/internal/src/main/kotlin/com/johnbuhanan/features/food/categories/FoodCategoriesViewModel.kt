package com.johnbuhanan.features.food.categories

import androidx.lifecycle.viewModelScope
import com.johnbuhanan.common.coroutines.di.IODispatcher
import com.johnbuhanan.common.coroutines.di.MainDispatcher
import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.common.viewmodel.UiEffect
import com.johnbuhanan.common.viewmodel.UiEvent
import com.johnbuhanan.common.viewmodel.UiState
import com.johnbuhanan.features.food.Food
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItems
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
    private val getFoodCategoriesAsItems: GetFoodCategoriesAsItems,
    private val router: Router,
) : BaseViewModel<FoodCategoriesEvent, FoodCategoriesState, FoodCategoriesEffect>(
    mainDispatcher,
    ioDispatcher
) {
    init {
        viewModelScope.launch(ioDispatcher) {
            getFoodCategoriesAsItems().fold(
                onSuccess = { categories ->
                    setState {
                        copy(categories = categories, isLoading = false)
                    }
                    setEffect { FoodCategoriesEffect.ShowToast("Food categories are loaded.") }
                },
                onFailure = {
                    setState { copy(isLoading = false) }
                    setEffect { FoodCategoriesEffect.ShowToast(it.message!!) }
                }
            )
        }
    }

    override fun setInitialState() = FoodCategoriesState(categories = listOf(), isLoading = true)

    override fun handleEvents(event: FoodCategoriesEvent) {
        when (event) {
            is FoodCategoriesEvent.TappedCategory -> {
                router.push(Food.Route.FoodCategoryDetails(event.id))
            }
            FoodCategoriesEvent.TappedBack -> router.pop()
        }
    }
}

sealed class FoodCategoriesEvent : UiEvent {
    data class TappedCategory(val id: String) : FoodCategoriesEvent()
    object TappedBack : FoodCategoriesEvent()
}

data class FoodCategoriesState(
    val categories: List<FoodItem> = listOf(),
    val isLoading: Boolean = false
) : UiState

sealed class FoodCategoriesEffect : UiEffect {
    data class ShowToast(val message: String) : FoodCategoriesEffect()
}