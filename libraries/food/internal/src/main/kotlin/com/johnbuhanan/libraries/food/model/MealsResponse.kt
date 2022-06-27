package com.johnbuhanan.libraries.food.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsResponse(
    val meals: List<Meal>,
)

@JsonClass(generateAdapter = true)
data class Meal(
    @Json(name = "idMeal") val id: String,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strMealThumb") val thumbnailUrl: String,
)
