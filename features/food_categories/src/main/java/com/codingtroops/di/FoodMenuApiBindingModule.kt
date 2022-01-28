package com.codingtroops.di

import com.codingtroops.networking.FoodMenuRepository
import com.codingtroops.networking.FoodMenuRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class FoodMenuApiBindingModule {
    @Binds
    @Singleton
    abstract fun binds(impl: FoodMenuRepositoryImpl): FoodMenuRepository
}
