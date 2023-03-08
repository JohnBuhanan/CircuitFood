package com.johnbuhanan.features.featureA.screen2

import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.featureA.api.FeatureA
import com.johnbuhanan.features.featureA.screen2.Screen2Event.TappedNext
import com.johnbuhanan.navigation.Router
import com.johnbuhanan.navigation.setResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class Screen2ViewModel @Inject constructor(
    private val router: Router,
) : BaseViewModel<Screen2Event, Screen2State, Screen2Effect>() {

    init {
        setState { copy(message = "FeatureA - Screen2") }
    }

    override fun setInitialState() = Screen2State("")

    override fun handleEvents(event: Screen2Event) {
        Timber.e("handleEvents")
        when (event) {
            is Screen2Event.TappedBack -> {
                router.setResult(FeatureA.Screen2Result("TESTING"))
                router.pop()
            }
            is TappedNext -> {
                router.push(FeatureA.Route.Screen3)
            }
            is Screen2Event.TappedShowBottomSheet -> {
                router.pushBottomSheet(FeatureA.Route.Screen3)
            }
        }
    }
}
