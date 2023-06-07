package com.johnbuhanan.common.di

import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
annotation class SingleIn(val scope: KClass<*>)