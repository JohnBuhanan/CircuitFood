package com.johnbuhanan.navigation.di

import com.johnbuhanan.navigation.Router
import com.johnbuhanan.navigation.RouterImpl
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
