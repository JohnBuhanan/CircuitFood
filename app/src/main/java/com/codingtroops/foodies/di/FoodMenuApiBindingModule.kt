package com.codingtroops.foodies.di

import com.codingtroops.foodies.model.data.FoodMenuRepository
import com.codingtroops.foodies.model.data.FoodMenuRepositoryImpl
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
