package com.johnbuhanan.features.featureA.api

import cafe.adriel.voyager.core.registry.ScreenProvider

interface FeatureA {
    object FeatureAInput
    object FeatureAOutput
    sealed class Route : ScreenProvider {
        object FeatureAScreen : Route()
    }
}
