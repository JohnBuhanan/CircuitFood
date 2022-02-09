package com.johnbuhanan.features.featureA.screen2

import com.johnbuhanan.common.BaseViewModel
import com.johnbuhanan.features.featureA.api.FeatureA
import com.johnbuhanan.features.featureA.screen2.Screen2Event.TappedNext
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class Screen2ViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val router: Router,
) : BaseViewModel<Screen2Event, Screen2State, Screen2Effect>(dispatcher) {

    init {
        setState { copy(message = "Screen2") }
    }

    override fun setInitialState() = Screen2State("")

    override fun handleEvents(event: Screen2Event) {
        Timber.e("handleEvents")
        when (event) {
            is TappedNext -> {
                router.push(FeatureA.Route.Screen3)
            }
        }
    }
}
