package com.johnbuhanan.libraries.food.usecase

import com.johnbuhanan.libraries.food.model.FoodItem

interface GetMealsAsItemsById {
    suspend operator fun invoke(id: String): Result<List<FoodItem>>
}
