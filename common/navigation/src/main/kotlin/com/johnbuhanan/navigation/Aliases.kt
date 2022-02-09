package com.johnbuhanan.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry

typealias Route = ScreenProvider
typealias ScreenModule = ScreenRegistry.() -> Unit
typealias ScreenModules = Set<@JvmSuppressWildcards ScreenModule>
