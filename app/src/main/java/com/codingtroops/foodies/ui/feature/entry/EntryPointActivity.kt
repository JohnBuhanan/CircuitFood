package com.codingtroops.foodies.ui.feature.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import com.codingtroops.common.AuroraNavigatorViewModel
import com.codingtroops.common.GenericNavGraph
import com.codingtroops.common.NavigatorEvent
import com.codingtroops.common.Routes
import com.codingtroops.foodies.ui.feature.entry.destinations.StartDestination
import com.codingtroops.foodies.ui.theme.ComposeSampleTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber


// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
//    @Inject
//    lateinit var navGraphs: Map<Class<out Routes>, @JvmSuppressWildcards NavGraphSpec>

    val navGraphs = mapOf<Routes, NavGraphSpec>(
        Routes.FoodCategories to com.codingtroops.NavGraphs.root
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSampleTheme {
                Initialize(navGraphs)
            }
        }
    }
}



@Composable
fun Initialize(
    navGraphs: Map<Routes, NavGraphSpec>,
) {
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
    val auroraNavigatorViewModel = hiltViewModel<AuroraNavigatorViewModel>()

    LaunchedEffect(Unit) {
        auroraNavigatorViewModel.destinations.collect {
            Timber.e("HERE!!!")
            when (val event = it) {
                is NavigatorEvent.NavigateUp -> destinationsNavController.navigateUp()
                is NavigatorEvent.Directions -> destinationsNavController.navigate(
                    direction = event.destination,
                    onlyIfResumed = false,
                    builder = event.builder,
                )
            }
        }
    }

    val appGraph = GenericNavGraph(
        route = "app",
        startDestination = StartDestination,
        destinations = listOf(
            StartDestination
        ),
        nestedNavGraphs = navGraphs.values.toList()
    )

    DestinationsNavHost(
        navGraph = appGraph,
        engine = engine,
        navController = navController,
    )
}
@Destination(start = true)
@Composable
fun Start(navigator: DestinationsNavigator) {
//    val thing = navGraphs[Routes.FoodCategories] as Direction
    navigator.navigate(com.codingtroops.NavGraphs.root)
}
