package com.codingtroops.foodies.ui.feature.categories

import androidx.lifecycle.viewModelScope
import com.codingtroops.common.AuroraNavigator
import com.codingtroops.common.BaseViewModel
import com.codingtroops.foodies.model.data.FoodMenuRepository
import com.codingtroops.foodies.ui.feature.destinations.FoodCategoryDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: FoodMenuRepository,
    private val auroraNavigator: AuroraNavigator,
) : BaseViewModel<FoodCategoriesEvent, FoodCategoriesState, FoodCategoriesEffect>(dispatcher), AuroraNavigator by auroraNavigator {

    init {
        start()
    }

    override fun setInitialState() = FoodCategoriesState(categories = listOf(), isLoading = true)

    override fun handleEvents(event: FoodCategoriesEvent) {
        Timber.e("handleEvents")
        when (event) {
            is FoodCategoriesEvent.CategorySelection -> {
                Timber.e("handleEvents - CategorySelection")
                auroraNavigator.navigate(FoodCategoryDetailsDestination(event.categoryName))
            }
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
