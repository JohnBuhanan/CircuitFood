package com.johnbuhanan.features.featureA.screen3

import com.johnbuhanan.common.coroutines.di.IODispatcher
import com.johnbuhanan.common.coroutines.di.MainDispatcher
import com.johnbuhanan.common.viewmodel.BaseViewModel
import com.johnbuhanan.features.featureA.screen3.Screen3Event.TappedNext
import com.johnbuhanan.features.featureB.api.FeatureB
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class Screen3ViewModel @Inject constructor(
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val router: Router,
) : BaseViewModel<Screen3Event, Screen3State, Screen3Effect>(mainDispatcher, ioDispatcher) {

    init {
        setState { copy(message = "FeatureA - Screen3") }
    }

    override fun setInitialState() = Screen3State("")

    override fun handleEvents(event: Screen3Event) {
        Timber.e("handleEvents")
        when (event) {
            is TappedNext -> {
                router.push(FeatureB.Route.Screen1)
            }
        }
    }
}
