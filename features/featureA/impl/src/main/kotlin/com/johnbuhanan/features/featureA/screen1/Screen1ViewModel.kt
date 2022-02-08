package com.johnbuhanan.features.featureA.screen1

import com.johnbuhanan.common.BaseViewModel
import com.johnbuhanan.features.featureA.api.FeatureA
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class Screen1ViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val router: Router,
) : BaseViewModel<Screen1Event, Screen1State, Screen1Effect>(dispatcher) {

    init {
        setState { copy(message = "Screen1") }
    }

    override fun setInitialState() = Screen1State("")

    override fun handleEvents(event: Screen1Event) {
        Timber.e("handleEvents")
        when (event) {
            is Screen1Event.TappedNext -> {
                router.push(FeatureA.Route.Screen2)
            }
        }
    }
}
