package com.johnbuhanan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.johnbuhanan.app.theme.ComposeSampleTheme
import com.johnbuhanan.common.FeatureNavGraphMap
import com.johnbuhanan.common.FeatureRoute
import com.johnbuhanan.common.Router
import com.johnbuhanan.common.RouterViewModel
import com.johnbuhanan.features.food.destinations.FoodCategoryDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
    @Inject
    lateinit var featureNavGraphMap: FeatureNavGraphMap

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSampleTheme {
                NavigationComponent(router, featureNavGraphMap)
            }
        }
    }
}

@Destination(start = true)
@Composable
fun Start() {
    Timber.e("Composable - Start")
    val routerViewModel = hiltViewModel<RouterViewModel>()
//    routerViewModel.goToFeature(FeatureRoute.Food)
    routerViewModel.synthesizeBackStack(
        listOf(
            FeatureRoute.Food,
            FoodCategoryDetailsScreenDestination("1"),
            FeatureRoute.FeatureA,
        )
    )
}
