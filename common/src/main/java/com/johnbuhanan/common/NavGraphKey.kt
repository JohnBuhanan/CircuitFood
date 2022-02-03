package com.johnbuhanan.common

import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.MapKey
import javax.inject.Provider
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NavGraphKey(val value: KClass<out FeatureRoute>)
typealias ScreenRoute = Direction
typealias FeatureNavGraph = NavGraphSpec
typealias NavGraphMap = Map<Class<out FeatureRoute>, @JvmSuppressWildcards Provider<FeatureNavGraph>>
