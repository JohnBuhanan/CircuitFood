package com.johnbuhanan.features.food.categories

import androidx.lifecycle.viewModelScope
import com.johnbuhanan.common.coroutines.di.IODispatcher
import com.johnbuhanan.common.coroutines.di.MainDispatcher
import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.food.Food
import com.johnbuhanan.features.food.domain.FoodMenuRepository
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
    private val repository: FoodMenuRepository,
    private val router: Router,
) : BaseViewModel<FoodCategoriesEvent, FoodCategoriesState, FoodCategoriesEffect>(mainDispatcher, ioDispatcher) {
    init {
        viewModelScope.launch(ioDispatcher) {
            repository.getFoodCategories().fold(
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
