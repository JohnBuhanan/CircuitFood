package com.johnbuhanan.common

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RouterModule {
    @Binds
    abstract fun bindsRouter(router: RouterImpl): Router
}
