package com.johnbuhanan.features.food.domain.mapper

import com.johnbuhanan.features.food.domain.model.FoodCategory
import com.johnbuhanan.features.food.domain.model.FoodItem
import com.johnbuhanan.features.food.domain.model.Meal

fun FoodCategory.toFoodItem(): FoodItem = FoodItem(
    id = id,
    name = name,
    description = description,
    thumbnailUrl = thumbnailUrl,
)

fun Meal.toFoodItem(): FoodItem = FoodItem(
    id = id,
    name = name,
    thumbnailUrl = thumbnailUrl
)