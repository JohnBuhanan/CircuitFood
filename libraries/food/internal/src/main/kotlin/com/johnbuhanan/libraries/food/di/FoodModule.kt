//package com.johnbuhanan.libraries.food.di
//
//import com.johnbuhanan.common.di.AppScope
//import com.johnbuhanan.libraries.food.FoodService
//import com.squareup.anvil.annotations.ContributesTo
//import dagger.Module
//import dagger.Provides
//import javax.inject.Singleton
//import retrofit2.Retrofit
//import retrofit2.create
//
//@ContributesTo(AppScope::class)
//@Module
//object FoodModule {
//    @Provides
//    @Singleton
//    fun provideFoodService(retrofit: Retrofit): FoodService = retrofit.create()
//}
