package com.codingtroops.di

import com.codingtroops.NavGraph
import com.codingtroops.common.NavGraphKey
import com.codingtroops.common.Routes
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

/**
 * This module builds a Map of all the Workers that we support in our app. This is used in the M1WorkerFactory to actually
 * create each Worker for use with WorkManager and Dagger.
 */
@Module
@InstallIn(SingletonComponent::class)
interface NavGraphBindingModule {
    @Binds
    @IntoMap
    @NavGraphKey(Routes.FoodCategories::class)
    fun bindPznService(factory: NavGraph): NavGraphSpec
}
