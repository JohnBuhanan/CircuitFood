package com.johnbuhanan.features.food.domain.usecase

import com.johnbuhanan.features.food.domain.mapper.toFoodItem
import com.johnbuhanan.features.food.domain.model.FoodItem
import com.johnbuhanan.features.food.domain.repository.MealRepository
import javax.inject.Inject
import javax.inject.Singleton

interface GetMealsAsItemsById {
    suspend operator fun invoke(id: String): Result<List<FoodItem>>
}

@Singleton
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