package com.johnbuhanan.libraries.food.di

import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.di.SingleIn
import com.johnbuhanan.libraries.food.FoodService
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@ContributesTo(AppScope::class)
@Module
object FoodModule {
    @Provides
    @SingleIn(AppScope::class)
    fun provideFoodService(retrofit: Retrofit): FoodService = retrofit.create()
}
