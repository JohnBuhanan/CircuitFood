package com.johnbuhanan.common

interface Route

sealed class FeatureRoute : Route {
    object Food : FeatureRoute()
    object FeatureA : FeatureRoute()
}
