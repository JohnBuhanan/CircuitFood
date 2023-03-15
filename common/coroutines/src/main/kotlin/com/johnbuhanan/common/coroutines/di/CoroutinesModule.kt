//package com.johnbuhanan.common.coroutines.di
//
//import com.squareup.anvil.annotations.ContributesTo
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//
//import dagger.hilt.components.SingletonComponent
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import javax.inject.Singleton
//
//
//@ContributesTo(AppScope::class)
//@Module
//class CoroutinesModule {
//    @[Provides Singleton IODispatcher]
//    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
//
//    @[Provides Singleton MainDispatcher]
//    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
//}
