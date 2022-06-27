package com.johnbuhanan.libraries.food.usecase

import com.johnbuhanan.libraries.food.model.FoodItem

interface GetFoodCategoriesAsItems {
    suspend operator fun invoke(): Result<List<FoodItem>>
}
