package com.johnbuhanan.libraries.food

import com.johnbuhanan.libraries.food.model.FoodCategoriesResponse
import com.johnbuhanan.libraries.food.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("categories.php")
    suspend fun getFoodCategories(): FoodCategoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategoryName(@Query("c") categoryName: String): MealsResponse
}
