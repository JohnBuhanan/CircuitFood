package com.johnbuhanan.app

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.johnbuhanan.navigation.ScreenModule
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class MVIPublicApp : Application() {
    @Inject
    lateinit var screenModules: Set<@JvmSuppressWildcards ScreenModule>

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())

        ScreenRegistry {
            screenModules.forEach {
                it()
            }
        }
    }
}
