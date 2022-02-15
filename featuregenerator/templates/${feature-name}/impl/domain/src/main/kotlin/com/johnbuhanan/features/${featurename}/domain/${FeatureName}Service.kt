package com.johnbuhanan.features.${featurename}.domain

import retrofit2.http.GET

interface ${FeatureName}Service
     @GET("")
     suspend fun getSomething(): String
}


