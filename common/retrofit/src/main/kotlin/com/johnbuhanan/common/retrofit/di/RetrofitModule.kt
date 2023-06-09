package com.johnbuhanan.common.retrofit.di

import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.di.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@ContributesTo(AppScope::class)
class RetrofitModule {
    companion object {
        const val API_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    @Provides
    @SingleIn(AppScope::class)
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @SingleIn(AppScope::class)
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(API_URL)
            .build()
    }
}
