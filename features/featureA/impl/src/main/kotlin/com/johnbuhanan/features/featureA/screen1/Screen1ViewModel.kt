package com.johnbuhanan.features.featureA.screen1

import com.johnbuhanan.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class Screen1ViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
) : BaseViewModel<Screen1Event, Screen1State, Screen1Effect>(dispatcher) {

    fun initialize(categoryId: String) {
        Timber.e("launchFoodItems")
        // I can't use IO dispatcher here for some reason???  It fails navigation when I do.
        setState { copy(thing = "LOADED!!!") }
    }

    override fun setInitialState() = Screen1State("")

    override fun handleEvents(event: Screen1Event) {
        Timber.e("handleEvents")
    }
}
