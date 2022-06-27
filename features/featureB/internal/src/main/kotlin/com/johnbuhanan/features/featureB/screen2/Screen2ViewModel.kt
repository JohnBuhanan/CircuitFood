package com.johnbuhanan.features.featureB.screen2

import com.johnbuhanan.common.coroutines.di.IODispatcher
import com.johnbuhanan.common.coroutines.di.MainDispatcher
import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.featureB.api.FeatureB
import com.johnbuhanan.features.featureB.screen2.Screen2Event.TappedNext
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class Screen2ViewModel @Inject constructor(
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val router: Router,
) : BaseViewModel<Screen2Event, Screen2State, Screen2Effect>(mainDispatcher, ioDispatcher) {

    init {
        setState { copy(message = "FeatureB - Screen2") }
    }

    override fun setInitialState() = Screen2State("")

    override fun handleEvents(event: Screen2Event) {
        Timber.e("handleEvents")
        when (event) {
            is TappedNext -> {
                router.push(FeatureB.Route.Screen3)
            }
        }
    }
}
