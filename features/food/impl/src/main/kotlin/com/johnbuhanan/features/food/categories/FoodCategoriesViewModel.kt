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
import timber.log.Timber
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
            val categories = repository.getFoodCategories()
            setState {
                copy(categories = categories, isLoading = false)
            }
            setEffect { FoodCategoriesEffect.ShowToast("Food categories are loaded.") }
        }
    }

    override fun setInitialState() = FoodCategoriesState(categories = listOf(), isLoading = true)

    override fun handleEvents(event: FoodCategoriesEvent) {
        Timber.e("handleEvents")
        when (event) {
            is FoodCategoriesEvent.TappedCategory -> {
                Timber.e("handleEvents - CategorySelection")
                router.push(Food.Route.FoodCategoryDetails(event.id))
            }
            FoodCategoriesEvent.TappedBack -> router.pop()
        }
    }
}
