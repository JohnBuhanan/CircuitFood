package com.johnbuhanan.features.featureA.screen1

import com.johnbuhanan.common.coroutines.di.IODispatcher
import com.johnbuhanan.common.coroutines.di.MainDispatcher
import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.featureA.api.FeatureA
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber

@HiltViewModel
class Screen1ViewModel @Inject constructor(
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val router: Router,
) : BaseViewModel<Screen1Event, Screen1State, Screen1Effect>(mainDispatcher, ioDispatcher) {

    init {
        setState { copy(message = "FeatureA - Screen1") }
    }

    override fun setInitialState() = Screen1State("")

    override fun handleEvents(event: Screen1Event) {
        Timber.e("handleEvents")
        when (event) {
            is Screen1Event.TappedNext -> {
                router.push(FeatureA.Route.Screen2)
            }
            is Screen1Event.Screen2ResultSet -> {
                setState { copy(message = "It bloody worked! ${event.value}") }
            }
        }
    }
}
