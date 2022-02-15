package com.americanexpress.android.${featurename}

import com.americanexpress.dagger.annotations.M1Msl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import com.americanexpress.android.${featurename}.serviceadapter.${FeatureName}Repository
import com.americanexpress.android.${featurename}.serviceadapter.${FeatureName}RepositoryImpl

@Module
@Suppress("EmptyClassBlock") // This class is empty because the service created by ratkt may not have been generated. Once you have a service definition in RAML please run gw ratkt and update this accordingly.
object ${FeatureName}Module {
//    @Provides
//    @JvmStatic
//    fun provide${FeatureName}Service (@M1Msl retrofit: Retrofit): ${FeatureName} = retrofit.create()

//    @Provides
//    @JvmStatic
//    fun provide${FeatureName}Repository (service: ${FeatureName}): ${FeatureName}Repository = ${FeatureName}RepositoryImpl(service)
}
