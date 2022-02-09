package com.johnbuhanan.features.featureA.api

import cafe.adriel.voyager.core.registry.ScreenProvider

interface FeatureB {
    object FeatureAInput
    object FeatureAOutput
    sealed class Route : ScreenProvider {
        object Screen1 : Route()
        object Screen2 : Route()
        object Screen3 : Route()
    }
}
