package com.johnbuhanan.app

import android.app.Application
import com.johnbuhanan.common.di.AppScope
import com.squareup.anvil.annotations.MergeComponent
import javax.inject.Singleton
import timber.log.Timber
import timber.log.Timber.DebugTree

@Singleton
@MergeComponent(AppScope::class)
interface AppComponent {
    fun inject(activity: EntryPointActivity)
}

class MVIPublicApp : Application() {

//    val daggerAppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}
