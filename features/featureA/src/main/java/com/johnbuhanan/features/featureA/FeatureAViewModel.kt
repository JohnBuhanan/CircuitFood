package com.johnbuhanan.features.featureA

import androidx.lifecycle.viewModelScope
import com.johnbuhanan.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FeatureAViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
) : BaseViewModel<FeatureAEvent, FeatureAState, FeatureAEffect>(dispatcher) {

    fun initialize(categoryId: String) {
        Timber.e("launchFoodItems")
        // I can't use IO dispatcher here for some reason???  It fails navigation when I do.
        viewModelScope.launch {
            setState { copy(thing = "LOADED!!!") }
        }
    }

    override fun setInitialState() = FeatureAState("")

    override fun handleEvents(event: FeatureAEvent) {
        Timber.e("handleEvents")
    }

    companion object {
        const val CATEGORY_ID = "categoryId"
    }
}
