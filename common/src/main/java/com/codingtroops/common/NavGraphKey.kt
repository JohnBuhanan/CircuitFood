package com.codingtroops.common

import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.MapKey
import javax.inject.Provider
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NavGraphKey(val value: KClass<out Route>)

typealias DirectionTransform = (Route) -> Direction
typealias NavGraphMap = Map<Class<out Route>, @JvmSuppressWildcards Provider<NavGraphSpec>>
typealias DirectionMap = Map<Class<out Route>, @JvmSuppressWildcards DirectionTransform>
