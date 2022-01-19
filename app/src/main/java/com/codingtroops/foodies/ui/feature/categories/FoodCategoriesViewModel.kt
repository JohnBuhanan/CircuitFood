package com.codingtroops.foodies.ui.feature.categories

import androidx.lifecycle.viewModelScope
import com.codingtroops.foodies.base.BaseViewModel
import com.codingtroops.foodies.model.data.FoodMenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: FoodMenuRepository,
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
                setEffect { FoodCategoriesEffect.Navigation.ToCategoryDetails(event.categoryName) }
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
