package com.johnbuhanan.features.${featurename}.viewmodel

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
class ${ScreenName}ViewModel @Inject constructor(
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
    private val repository: ${FeatureName}Repository,
    private val router: Router,
) : BaseViewModel<${ScreenName}Event, ${ScreenName}State, ${ScreenName}Effect>(mainDispatcher, ioDispatcher) {
    init {
        viewModelScope.launch(ioDispatcher) {
            val categories = repository.getSomething()
            setState {
                copy(isLoading = false)
            }
            setEffect { ${ScreenName}Effect.ShowToast("Something is loaded!") }
        }
    }

    override fun setInitialState() = ${ScreenName}State(isLoading = true)

    override fun handleEvents(event: ${ScreenName}Event) {
        Timber.e("handleEvents")
        when (event) {
            ${ScreenName}Event.TappedBack -> router.pop()
        }
    }
}

sealed class ${ScreenName}Event : UiEvent {
    object TappedBack : ${ScreenName}Event()
}

data class ${ScreenName}State(val isLoading: Boolean = false) : UiState

sealed class ${ScreenName}Effect : UiEffect {
    data class ShowToast(val message: String) : ${ScreenName}Effect()
}