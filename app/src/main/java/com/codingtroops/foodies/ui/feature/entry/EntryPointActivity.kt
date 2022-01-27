package com.codingtroops.foodies.ui.feature.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.codingtroops.common.AuroraNavigatorViewModel
import com.codingtroops.common.NavigatorEvent
import com.codingtroops.foodies.ui.feature.NavGraphs
import com.codingtroops.foodies.ui.feature.destinations.FoodCategoriesDestination
import com.codingtroops.foodies.ui.theme.ComposeSampleTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

@Destination(start = true)
@Composable
fun Start(
    navigator: DestinationsNavigator,
) {
    val auroraNavigatorViewModel = hiltViewModel<AuroraNavigatorViewModel>()

    LaunchedEffect(Unit) {
        auroraNavigatorViewModel.destinations.collect {
            when (val event = it) {
                is NavigatorEvent.NavigateUp -> navigator.navigateUp()
                is NavigatorEvent.Directions -> navigator.navigate(
                    direction = event.destination,
                    onlyIfResumed = true,
                    builder = event.builder,
                )
            }
        }
    }
    auroraNavigatorViewModel.navigate(FoodCategoriesDestination)
}
