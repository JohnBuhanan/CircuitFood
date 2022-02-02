package com.johnbuhanan.common

sealed class Route {
    open class FoodFeature : Route() {
        data class FoodCategoryDetails(val id: String) : FoodFeature()
    }

    object FeatureA : Route()
}
