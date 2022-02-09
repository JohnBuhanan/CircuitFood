package com.johnbuhanan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.johnbuhanan.app.theme.ComposeSampleTheme
import com.johnbuhanan.features.food.Food.Route.FoodCategories
import com.johnbuhanan.navigation.Router
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSampleTheme {
                NavigatorWrapper(router)
            }
        }
    }
}

class StartScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        Timber.e("Composable - Start")

        val navigator = LocalNavigator.currentOrThrow
//        remember(FoodCategories) {
//            ScreenRegistry.get(FoodCategories)
//        }
//        getScreen = { foodCategories }
        getNavigator = { navigator }
        val screen = rememberScreen(FoodCategories)
        navigator.push(screen)
    }
}