package com.johnbuhanan.features.food.domain.di

import com.johnbuhanan.features.food.domain.FoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FoodModule {
    @Provides
    @Singleton
    fun provideFoodService(retrofit: Retrofit): FoodService = retrofit.create()
}
