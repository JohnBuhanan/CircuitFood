package com.johnbuhanan.features.food

import com.slack.circuit.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
object CounterScreen : Screen

@Parcelize
object FoodCategoriesScreen : Screen

@Parcelize
data class FoodCategoryDetailsScreen(val id: String) : Screen