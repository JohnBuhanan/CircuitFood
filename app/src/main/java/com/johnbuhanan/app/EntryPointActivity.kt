package com.johnbuhanan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import com.johnbuhanan.app.destinations.StartDestination
import com.johnbuhanan.app.theme.ComposeSampleTheme
import com.johnbuhanan.common.GenericNavGraph
import com.johnbuhanan.common.NavGraphMap
import com.johnbuhanan.common.RouterViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
    @Inject
    lateinit var navGraphMap: NavGraphMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appGraph = GenericNavGraph(
            route = "app",
            startDestination = StartDestination,
            destinations = listOf(
                StartDestination
            ),
            nestedNavGraphs = navGraphMap.values.map { it.get() }
        )
        setContent {
            ComposeSampleTheme {
                Initialize(appGraph)
            }
        }
    }
}

@Composable
fun Initialize(appGraph: GenericNavGraph) {
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    val navBackStackEntry = NavBackStackEntry.create(
        context = LocalContext.current,
        destination = NavDestination(""),
        hostLifecycleState = RESUMED
    )
    val destinationsNavController = DestinationsNavController(
        navController = navController,
        navBackStackEntry = navBackStackEntry,
    )
    val routerViewModel = hiltViewModel<RouterViewModel>()
    routerViewModel.initRouter(destinationsNavController)

    DestinationsNavHost(
        navGraph = appGraph,
        engine = engine,
        navController = navController,
    )
}

@Destination(start = true)
@Composable
fun Start(navigator: DestinationsNavigator) {
    navigator.navigate(NavGraphs.root)
}
