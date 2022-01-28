package com.codingtroops.common

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

data class GenericNavGraph(
    override val route: String,
    override val startDestination: DestinationSpec<*>,
    val destinations: List<DestinationSpec<*>>,
    override val nestedNavGraphs: List<NavGraphSpec> = emptyList(),
) : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = destinations.associateBy { it.route }
}
