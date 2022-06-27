package com.johnbuhanan.libraries.food.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodCategoriesResponse(
    @Json(name = "categories") val foodCategories: List<FoodCategory>,
)

@JsonClass(generateAdapter = true)
data class FoodCategory(
    @Json(name = "idCategory") val id: String,
    @Json(name = "strCategory") val name: String,
    @Json(name = "strCategoryThumb") val thumbnailUrl: String,
    @Json(name = "strCategoryDescription") val description: String = "",
)
