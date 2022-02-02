package com.johnbuhanan.common

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

data class GenericNavGraph(
    override val route: String,
    override val startDestination: DestinationSpec<*>,
    val destinations: List<DestinationSpec<*>> = emptyList(),
    override val nestedNavGraphs: List<NavGraphSpec> = emptyList(),
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = destinations.associateBy { it.route },
) : NavGraphSpec

fun NavGraphSpec.toGeneric() = GenericNavGraph(
    route = route,
    startDestination = startDestination,
    nestedNavGraphs = nestedNavGraphs,
    destinationsByRoute = destinationsByRoute,
)
