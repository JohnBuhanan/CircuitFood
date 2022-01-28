package com.johnbuhanan.common

import java.io.Serializable

abstract class Route(val navGraphName: String) : Serializable {
    object Splash : Route("Splash")
    object FoodCategories : Route("FoodCategories")
    data class FoodCategoryDetails(val id: String) : Route("FoodCategoryDetails")
    object FeatureA : Route("FeatureA")
}
