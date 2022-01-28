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
import com.codingtroops.common.NavGraph
import com.codingtroops.common.NavigatorEvent
import com.codingtroops.common.Routes
import com.codingtroops.foodies.ui.feature.destinations.FoodCategoriesDestination
import com.codingtroops.foodies.ui.theme.ComposeSampleTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject


// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
    @Inject
    lateinit var navGraphs: Map<Class<out Routes>, @JvmSuppressWildcards NavGraph>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSampleTheme {
                Start(navGraphs)
            }
        }
    }
}

@Composable
fun Start(
    navGraphs: Map<Class<out Routes>, @JvmSuppressWildcards NavGraph>,
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

    val appGraph = NavGraph(
        route = "app",
        startDestination = FoodCategoriesDestination,
        destinations = emptyList(),
        nestedNavGraphs = navGraphs.values.toList()
    )

    DestinationsNavHost(
        navGraph = appGraph,
        engine = engine,
        navController = navController,
    )
}
