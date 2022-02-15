package com.johnbuhanan.features.food.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodCategoriesResponse(val categories: List<FoodCategoryResponse>)

@JsonClass(generateAdapter = true)
data class MealsResponse(val meals: List<MealResponse>)

@JsonClass(generateAdapter = true)
data class FoodCategoryResponse(
    @Json(name = "idCategory") val id: String,
    @Json(name = "strCategory") val name: String,
    @Json(name = "strCategoryThumb") val thumbnailUrl: String,
    @Json(name = "strCategoryDescription") val description: String = "",
)

@JsonClass(generateAdapter = true)
data class MealResponse(
    @Json(name = "idMeal") val id: String,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strMealThumb") val thumbnailUrl: String,
)
