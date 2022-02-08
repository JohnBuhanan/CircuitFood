package com.johnbuhanan.features.food.details

import androidx.lifecycle.viewModelScope
import com.johnbuhanan.common.BaseViewModel
import com.johnbuhanan.features.food.details.FoodCategoryDetailsEvent.TappedBack
import com.johnbuhanan.features.food.details.FoodCategoryDetailsEvent.TappedFoodItem
import com.johnbuhanan.features.food.domain.FoodMenuRepository
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: FoodMenuRepository,
    private val router: Router,
) : BaseViewModel<FoodCategoryDetailsEvent, FoodCategoryDetailsState, FoodCategoryDetailsEffect>(dispatcher) {

    fun initialize(categoryId: String) {
        Timber.e("initialize")

        viewModelScope.launch(dispatcher) {
            val categories = repository.getFoodCategories()
            val category = categories.first { it.id == categoryId }
            setState { copy(category = category) }

            val foodItems = repository.getMealsByCategory(categoryId)
            setState { copy(categoryFoodItems = foodItems) }
        }
    }

    override fun setInitialState() = FoodCategoryDetailsState(null, listOf())

    override fun handleEvents(event: FoodCategoryDetailsEvent) {
        Timber.e("handleEvents")
        when (event) {
            TappedBack -> router.pop()
            is TappedFoodItem -> {
                Timber.e("TappedFoodItem")
                setEffect { FoodCategoryDetailsEffect.ShowToast(event.message) }
            }
        }
    }
}
