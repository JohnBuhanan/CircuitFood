package com.johnbuhanan.libraries.food.mapper

import com.johnbuhanan.libraries.food.model.FoodCategory
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.model.Meal

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