package com.johnbuhanan.features.${featurename}.domain.di

import com.johnbuhanan.features.${featurename}.domain.${FeatureName}Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ${FeatureName}Module {
    @Provides
    @Singleton
    fun provide${FeatureName}Service(retrofit: Retrofit): ${FeatureName}Service = retrofit.create()
}
