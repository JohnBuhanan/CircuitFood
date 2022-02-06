package com.johnbuhanan.features.featureA.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FeatureANavGraphModule {
//    @Provides
//    @IntoMap
//    @NavGraphKey(FeatureRoute.FeatureA::class)
//    fun provideNavGraphSpec(): NavGraphSpec {
//        return NavGraphs.featureA
//    }

    // TODO add more destinations for featureA
//    @Provides
//    @IntoMap
//    @NavGraphKey(Route.FoodCategoryDetails::class)
//    fun provideDirections(): DirectionTransform {
//        return {
//            val id = (it as Route.FoodCategoryDetails).id
//            FoodCategoryDetailsScreenDestination(id)
//        }
//    }
}
