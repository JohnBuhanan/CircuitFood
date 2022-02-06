package com.johnbuhanan.features.food.shared

data class FoodItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
)
