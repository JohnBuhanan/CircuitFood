package com.codingtroops.common

import java.io.Serializable

abstract class Routes(val navGraphName: String) : Serializable {
    object Splash : Routes("Splash")
    object FoodCategories : Routes("FoodCategories")
    data class FoodCategoryDetails(val id: String) : Routes("FoodCategoryDetails")
    object FeatureA : Routes("FeatureA")
}
