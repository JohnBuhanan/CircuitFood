package com.johnbuhanan.libraries.food.usecase

import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.di.SingleIn
import com.johnbuhanan.libraries.food.mapper.toFoodItem
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.repository.MealRepository
import javax.inject.Inject

@SingleIn(AppScope::class)
class GetMealsAsItemsByIdImpl @Inject constructor(
    private val repository: MealRepository,
    private val getFoodCategoryAsItemById: GetFoodCategoryAsItemById,
) : GetMealsAsItemsById {
    override suspend operator fun invoke(id: String): Result<List<FoodItem>> {
        return getFoodCategoryAsItemById(id).flatMap { foodItem ->
            repository.getMealsByCategoryName(foodItem.name).mapCatching { meals ->
                meals.map { it.toFoodItem() }
            }
        }
    }
}

suspend fun <T, R> Result<T>.flatMap(block: suspend (T) -> (Result<R>)): Result<R> {
    return this.mapCatching {
        block(it).getOrThrow()
    }
}