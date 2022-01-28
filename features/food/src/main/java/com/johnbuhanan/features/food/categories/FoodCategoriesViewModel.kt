package com.johnbuhanan.features.food.categories

import androidx.lifecycle.viewModelScope
import com.johnbuhanan.common.BaseViewModel
import com.johnbuhanan.common.Route
import com.johnbuhanan.common.Router
import com.johnbuhanan.features.food.shared.FoodMenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: FoodMenuRepository,
    private val router: Router,
) : BaseViewModel<FoodCategoriesEvent, FoodCategoriesState, FoodCategoriesEffect>(dispatcher) {

    init {
        start()
    }

    override fun setInitialState() = FoodCategoriesState(categories = listOf(), isLoading = true)

    override fun handleEvents(event: FoodCategoriesEvent) {
        Timber.e("handleEvents")
        when (event) {
            is FoodCategoriesEvent.CategorySelection -> {
                Timber.e("handleEvents - CategorySelection")
                router.goTo(Route.FoodCategoryDetails(event.categoryName))
            }
            FoodCategoriesEvent.TappedBack -> router.goBack()
        }
    }

    private fun start() {
        viewModelScope.launch(dispatcher) {
            val categories = repository.getFoodCategories()
            setState {
                copy(categories = categories, isLoading = false)
            }
            setEffect { FoodCategoriesEffect.DataWasLoaded }
        }
    }
}
