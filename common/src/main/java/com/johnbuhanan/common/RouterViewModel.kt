package com.johnbuhanan.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RouterViewModel @Inject constructor(
    private val router: Router,
    private val navGraphMap: NavGraphMap,
    private val directionMap: DirectionMap,
) : ViewModel(), Router by router {

    fun initRouter(destinationsNavController: DestinationsNavController) {
        viewModelScope.launch {
            routerEvents.collect {
                Timber.e("HERE!!!")
                when (val event = it) {
                    is RouterEvent.GoBack -> destinationsNavController.navigateUp()
                    is RouterEvent.GoTo -> {
                        val eventRoute = event.route
                        val clazz = eventRoute::class.java
                        val navGraphRoute = navGraphMap[clazz]?.get()?.route
                        val destinationRoute = directionMap[clazz]?.invoke(eventRoute)?.route
                        val route = navGraphRoute ?: destinationRoute ?: ""

                        destinationsNavController.navigate(
                            route = route,
                            onlyIfResumed = false,
                            builder = event.builder,
                        )
                    }
                }
            }
        }
    }
}
