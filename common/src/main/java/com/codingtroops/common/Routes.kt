package com.codingtroops.common

import java.io.Serializable

abstract class Routes() : Serializable {
    object Splash : Routes()
    object FoodCategories : Routes()
    data class FoodCategoryDetails(val id: String) : Routes()
    object FeatureA : Routes()
}
