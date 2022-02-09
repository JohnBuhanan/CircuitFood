package com.johnbuhanan.features.featureB.screen3

import com.johnbuhanan.common.BaseViewModel
import com.johnbuhanan.features.featureB.api.FeatureB
import com.johnbuhanan.features.featureB.screen3.Screen3Event.TappedNext
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class Screen3ViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val router: Router,
) : BaseViewModel<Screen3Event, Screen3State, Screen3Effect>(dispatcher) {

    init {
        setState { copy(message = "FeatureB - Screen3") }
    }

    override fun setInitialState() = Screen3State("")

    override fun handleEvents(event: Screen3Event) {
        Timber.e("handleEvents")
        when (event) {
            is TappedNext -> {
                router.push(FeatureB.Route.Screen3)
            }
        }
    }
}
