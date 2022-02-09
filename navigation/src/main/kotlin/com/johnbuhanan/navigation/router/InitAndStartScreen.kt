package com.johnbuhanan.navigation.router

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.johnbuhanan.features.food.Food
import com.johnbuhanan.navigation.getNavigator
import timber.log.Timber

class InitAndStartScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        Timber.e("Composable - Start")

        val navigator = LocalNavigator.currentOrThrow
        getNavigator = { navigator }

        val screen = rememberScreen(Food.Route.FoodCategories)
        navigator.push(screen)
    }
}