package com.codingtroops.foodies.ui.feature.category_details

import androidx.lifecycle.viewModelScope
import com.codingtroops.common.AuroraNavigator
import com.codingtroops.common.BaseViewModel
import com.codingtroops.featureA.NavGraphs
import com.codingtroops.foodies.model.data.FoodMenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: FoodMenuRepository,
    private val auroraNavigator: AuroraNavigator,
) : BaseViewModel<FoodCategoryDetailsEvent, FoodCategoryDetailsState, FoodCategoryDetailsEffect>(dispatcher) {

    fun initialize(categoryId: String) {
        Timber.e("launchFoodItems")
        // I can't use IO dispatcher here for some reason???  It fails navigation when I do.
        viewModelScope.launch {
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
            FoodCategoryDetailsEvent.TappedBack -> auroraNavigator.navigateUp()
            is FoodCategoryDetailsEvent.TappedFoodItem -> auroraNavigator.navigate(NavGraphs.featureA)
        }
    }

    companion object {
        const val CATEGORY_ID = "categoryId"
    }
}
