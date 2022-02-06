package com.johnbuhanan.app

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.johnbuhanan.features.food.di.featureScreenModule
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class MVIPublicApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())

        ScreenRegistry {
            featureScreenModule()
        }
    }
}
