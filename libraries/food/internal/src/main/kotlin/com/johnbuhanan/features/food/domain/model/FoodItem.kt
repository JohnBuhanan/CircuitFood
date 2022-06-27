package com.johnbuhanan.features.food.domain.model

data class FoodItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = "",
)
