package com.johnbuhanan.features.featureB.api

import cafe.adriel.voyager.core.registry.ScreenProvider

interface FeatureB {
    object FeatureBInput
    object FeatureBOutput
    sealed class Route : ScreenProvider {
        object Screen1 : Route()
        object Screen2 : Route()
        object Screen3 : Route()
    }
}
