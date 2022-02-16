package com.johnbuhanan.features.food.details

import androidx.lifecycle.viewModelScope
import com.johnbuhanan.common.coroutines.di.IODispatcher
import com.johnbuhanan.common.coroutines.di.MainDispatcher
import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.featureA.api.FeatureA
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
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: FoodMenuRepository,
    private val router: Router,
) : BaseViewModel<FoodCategoryDetailsEvent, FoodCategoryDetailsState, FoodCategoryDetailsEffect>(mainDispatcher, ioDispatcher) {

    fun initialize(categoryId: String) {
        Timber.e("initialize")

        viewModelScope.launch(ioDispatcher) {
            repository.getFoodCategories().fold(
                onSuccess = { categories ->
                    val category = categories.first { it.id == categoryId }
                    setState { copy(category = category) }
                },
                onFailure = {
                    setEffect { FoodCategoryDetailsEffect.ShowToast(it.message!!) }
                }
            )
            repository.getMealsByCategory(categoryId).fold(
                onSuccess = { foodItems ->
                    setState { copy(categoryFoodItems = foodItems) }
                },
                onFailure = {
                    setEffect { FoodCategoryDetailsEffect.ShowToast(it.message!!) }
                }
            )
        }
    }

    override fun setInitialState() = FoodCategoryDetailsState(null, listOf())

    override fun handleEvents(event: FoodCategoryDetailsEvent) {
        Timber.e("handleEvents")
        when (event) {
            TappedBack -> router.pop()
            is TappedFoodItem -> {
                Timber.e("TappedFoodItem")
                router.push(FeatureA.Route.Screen1)
                // setEffect { FoodCategoryDetailsEffect.ShowToast(event.message) }
            }
        }
    }
}
