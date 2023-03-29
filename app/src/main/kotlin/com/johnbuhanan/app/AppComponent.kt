package com.johnbuhanan.app

import com.johnbuhanan.common.di.AppScope
import com.squareup.anvil.annotations.MergeComponent
import javax.inject.Singleton

@Singleton
@MergeComponent(AppScope::class)
interface AppComponent {
    fun inject(activity: EntryPointActivity)
}