package com.johnbuhanan.features.food.domain

import com.johnbuhanan.features.food.domain.model.FoodCategoriesResponse
import com.johnbuhanan.features.food.domain.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("categories.php")
    suspend fun getFoodCategories(): FoodCategoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryId: String): MealsResponse
}
