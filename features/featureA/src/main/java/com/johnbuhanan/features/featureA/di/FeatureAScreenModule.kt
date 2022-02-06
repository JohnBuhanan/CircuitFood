package com.johnbuhanan.features.featureA.di

import cafe.adriel.voyager.core.registry.screenModule
import com.johnbuhanan.features.featureA.FeatureAScreen
import com.johnbuhanan.navigation.ScreenModule
import com.johnbuhanan.navigation.SharedScreen
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
            register<SharedScreen.FeatureA> {
                FeatureAScreen()
            }
        }
    }
}
