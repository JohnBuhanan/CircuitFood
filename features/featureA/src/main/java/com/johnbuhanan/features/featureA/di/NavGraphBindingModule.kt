package com.johnbuhanan.features.featureA.di

import com.johnbuhanan.common.NavGraphKey
import com.johnbuhanan.common.Route
import com.johnbuhanan.features.featureA.NavGraphs
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
object NavGraphModule {
    @Provides
    @IntoMap
    @NavGraphKey(Route.FeatureA::class)
    fun provideNavGraphSpec(): NavGraphSpec {
        return NavGraphs.featureA
    }

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
