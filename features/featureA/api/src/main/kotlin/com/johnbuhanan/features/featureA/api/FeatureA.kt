package com.johnbuhanan.features.featureA.api

import cafe.adriel.voyager.core.registry.ScreenProvider

interface FeatureA {
    object FeatureAInput
    object FeatureAOutput
    sealed class Route : ScreenProvider {
        object Screen1 : Route()
        data class Screen2(val onResult: (Long) -> Unit) : Route()
        object Screen3 : Route()
    }
}
