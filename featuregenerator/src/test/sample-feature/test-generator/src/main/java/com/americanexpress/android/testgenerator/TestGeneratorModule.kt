package com.americanexpress.android.testgenerator

import com.americanexpress.dagger.annotations.M1Msl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import com.americanexpress.android.testgenerator.serviceadapter.TestGeneratorRepository
import com.americanexpress.android.testgenerator.serviceadapter.TestGeneratorRepositoryImpl

@Module
@Suppress("EmptyClassBlock") // This class is empty because the service created by ratkt may not have been generated. Once you have a service definition in RAML please run gw ratkt and update this accordingly.
object TestGeneratorModule {
//    @Provides
//    @JvmStatic
//    fun provideTestGeneratorService (@M1Msl retrofit: Retrofit): TestGenerator = retrofit.create()

//    @Provides
//    @JvmStatic
//    fun provideTestGeneratorRepository (service: TestGenerator): TestGeneratorRepository = TestGeneratorRepositoryImpl(service)
}
