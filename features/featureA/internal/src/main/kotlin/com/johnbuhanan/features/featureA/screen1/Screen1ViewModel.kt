package com.johnbuhanan.features.featureA.screen1

import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.featureA.api.FeatureA
import com.johnbuhanan.navigation.Router
import com.johnbuhanan.navigation.listenForResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class Screen1ViewModel @Inject constructor(
    private val router: Router,
) : BaseViewModel<Screen1Event, Screen1State, Screen1Effect>() {

    init {
        setState { copy(message = "FeatureA - Screen1", buttonText = "Launch for result!") }

        router.listenForResult<FeatureA.Screen2Result>(this) {
            setState { copy(message = "Screen2Result: ${it.value}") }
        }
    }

    override fun setInitialState() = Screen1State("", "")

    override fun handleEvents(event: Screen1Event) {
        Timber.e("handleEvents")
        when (event) {
            is Screen1Event.TappedNext -> {
                router.push(FeatureA.Route.Screen2)
            }
        }
    }
}
