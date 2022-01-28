package com.codingtroops.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuroraNavigatorViewModel @Inject constructor(
    private val auroraNavigator: AuroraNavigator,
    private val navGraphMap: NavGraphMap,
    private val directionMap: DirectionMap,
) : ViewModel(), AuroraNavigator by auroraNavigator {

    fun initialize(destinationsNavController: DestinationsNavController) {
        viewModelScope.launch {
            destinations.collect {
                Timber.e("HERE!!!")
                when (val event = it) {
                    is NavigatorEvent.NavigateUp -> destinationsNavController.navigateUp()
                    is NavigatorEvent.Directions -> {
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
