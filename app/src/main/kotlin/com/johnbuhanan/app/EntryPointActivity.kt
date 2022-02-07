package com.johnbuhanan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.johnbuhanan.app.theme.ComposeSampleTheme
import com.johnbuhanan.features.food.Food
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSampleTheme {
                Navigator(StartScreen())
            }
        }
    }
}

class StartScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        Timber.e("Composable - Start")
        val foodCategories = rememberScreen(Food.Route.FoodCategories)
        val navigator = LocalNavigator.currentOrThrow

        navigator.push(foodCategories)
    }
}
