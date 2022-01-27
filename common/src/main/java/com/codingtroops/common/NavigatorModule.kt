package com.codingtroops.common

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorModule {
    @Binds
    abstract fun navigator(navigator: AuroraNavigatorImpl): AuroraNavigator
}
