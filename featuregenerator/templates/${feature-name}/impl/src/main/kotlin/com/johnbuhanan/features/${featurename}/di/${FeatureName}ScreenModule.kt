package com.johnbuhanan.features.${featurename}.di

import cafe.adriel.voyager.core.registry.screenModule
import com.johnbuhanan.features.${featurename}.api.${FeatureName}
import com.johnbuhanan.features.${featurename}.screen.${EachScreen}Screen
import com.johnbuhanan.navigation.ScreenModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object `${FeatureName}ScreenModule` {
    @Provides
    @IntoSet
    fun provideScreenModule(): ScreenModule {
        return screenModule {
            register<${FeatureName}.Route.${EachScreen}> { ${EachScreen}Screen() }
        }
    }
}
