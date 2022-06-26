package com.johnbuhanan.features.featureA.screen2

import com.johnbuhanan.common.coroutines.di.IODispatcher
import com.johnbuhanan.common.coroutines.di.MainDispatcher
import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.featureA.api.FeatureA
import com.johnbuhanan.features.featureA.screen2.Screen2Event.TappedNext
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

    lateinit var onResult: (Long) -> Unit

    init {
        setState { copy(message = "FeatureA - Screen2") }
    }

    override fun setInitialState() = Screen2State("")

    override fun handleEvents(event: Screen2Event) {
        Timber.e("handleEvents")
        when (event) {
            is Screen2Event.TappedBack -> {
                onResult(System.currentTimeMillis())
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
