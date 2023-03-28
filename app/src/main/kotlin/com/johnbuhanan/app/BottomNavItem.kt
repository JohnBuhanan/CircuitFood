package com.johnbuhanan.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.johnbuhanan.features.about.AboutScreen
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.slack.circuit.Screen

sealed class BottomNavItem(val title: String, val icon: ImageVector) {
    abstract val screen: Screen

    object FoodNavItem : BottomNavItem("Food", Icons.Filled.Home) {
        override val screen: Screen = FoodCategoriesScreen
    }

    object AboutNavItem : BottomNavItem("About", Icons.Filled.Info) {
        override val screen: Screen = AboutScreen
    }
}