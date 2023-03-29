package com.johnbuhanan.app

import android.app.Application
import com.johnbuhanan.common.di.AppScope
import com.squareup.anvil.annotations.MergeComponent
import javax.inject.Singleton
import timber.log.Timber
import timber.log.Timber.DebugTree

class MVIPublicApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}
