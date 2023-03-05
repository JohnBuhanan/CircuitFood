package com.johnbuhanan.features.featureA.api

import cafe.adriel.voyager.core.registry.ScreenProvider
import java.io.Serializable

interface FeatureA {
    object FeatureAInput
    object FeatureAOutput
    sealed class Route : ScreenProvider, Serializable {
        object Screen1 : Route()
        object Screen2 : Route()
        object Screen3 : Route()
    }
}
