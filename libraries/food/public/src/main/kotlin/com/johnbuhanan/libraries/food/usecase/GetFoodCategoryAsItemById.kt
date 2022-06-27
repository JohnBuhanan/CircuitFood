package com.johnbuhanan.libraries.food.usecase

import com.johnbuhanan.libraries.food.model.FoodItem

interface GetFoodCategoryAsItemById {
    suspend operator fun invoke(categoryId: String): Result<FoodItem>
}
