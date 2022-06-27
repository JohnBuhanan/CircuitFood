package com.johnbuhanan.libraries.food.model

data class FoodItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = "",
)
