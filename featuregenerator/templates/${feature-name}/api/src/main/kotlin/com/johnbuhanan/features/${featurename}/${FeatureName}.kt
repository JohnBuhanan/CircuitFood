package com.johnbuhanan.features.${featurename}

import cafe.adriel.voyager.core.registry.ScreenProvider

interface ${FeatureName} {
    object ${FeatureName}Input
    object ${FeatureName}Output
    sealed class Route : ScreenProvider {
        // object Screen1 : Route()
        // object Screen2 : Route()
    }
}
