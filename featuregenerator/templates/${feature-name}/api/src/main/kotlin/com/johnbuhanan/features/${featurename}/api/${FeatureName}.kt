package com.johnbuhanan.features.${featurename}.api

import cafe.adriel.voyager.core.registry.ScreenProvider

interface ${FeatureName} {
    object ${FeatureName}Input
    object ${FeatureName}Output
    sealed class Route : ScreenProvider {
         object ${EachScreen} : Route()
    }
}
