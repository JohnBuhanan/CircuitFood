package com.johnbuhanan.features.${featurename}.domain.di

import com.johnbuhanan.features.${featurename}.domain.${FeatureName}Repository
import com.johnbuhanan.features.${featurename}.domain.${FeatureName}RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ${FeatureName}BindingModule {
    @Binds
    @Singleton
    abstract fun binds(impl: ${FeatureName}RepositoryImpl): ${FeatureName}Repository
}
