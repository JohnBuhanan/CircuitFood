package com.johnbuhanan.features.featureA.di

import cafe.adriel.voyager.core.registry.screenModule
import com.johnbuhanan.features.featureA.api.FeatureA
import com.johnbuhanan.features.featureA.screen1.Screen2Screen
import com.johnbuhanan.navigation.ScreenModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object FeatureAScreenModule {
    @Provides
    @IntoSet
    fun provideScreenModule(): ScreenModule {
        return screenModule {
            register<FeatureA.Route.Screen1> {
                Screen2Screen()
            }
        }
    }
}
