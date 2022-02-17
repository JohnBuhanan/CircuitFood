package com.johnbuhanan.features.${featurename}.domain.repository

import com.johnbuhanan.features.${featurename}.domain.service.${FeatureName}Service
import javax.inject.Inject
import javax.inject.Singleton

interface ${FeatureName}Repository {
    suspend fun getSomething(): String
}

@Singleton
class ${FeatureName}RepositoryImpl @Inject constructor(private val service: ${FeatureName}Service) : ${FeatureName}Repository {
    override suspend fun getSomething(): String {
        return service.getSomething()
    }
}
