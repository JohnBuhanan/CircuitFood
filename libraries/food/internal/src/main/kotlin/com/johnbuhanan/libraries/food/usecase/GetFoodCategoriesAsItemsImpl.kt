package com.johnbuhanan.libraries.food.usecase

import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.di.SingleIn
import com.johnbuhanan.libraries.food.mapper.toFoodItem
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.repository.FoodCategoryRepository
import javax.inject.Inject

@SingleIn(AppScope::class)
class GetFoodCategoriesAsItemsImpl @Inject constructor(
    private val repository: FoodCategoryRepository,
) : GetFoodCategoriesAsItems {
    override suspend operator fun invoke(): Result<List<FoodItem>> {
        return repository.getFoodCategories().mapCatching { foodCategories ->
            foodCategories.map { foodCategory ->
                foodCategory.toFoodItem()
            }
        }
    }
}