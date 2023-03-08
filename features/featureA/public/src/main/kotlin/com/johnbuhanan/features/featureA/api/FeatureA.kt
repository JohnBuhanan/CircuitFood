package com.johnbuhanan.features.featureA.api

import cafe.adriel.voyager.core.registry.ScreenProvider
import java.io.Serializable

interface FeatureA {
    sealed class Route : ScreenProvider, Serializable {
        object Screen1 : Route()
        object Screen2 : Route()
        object Screen3 : Route()
    }

    data class Screen2Result(val value: String) : Serializable
}
