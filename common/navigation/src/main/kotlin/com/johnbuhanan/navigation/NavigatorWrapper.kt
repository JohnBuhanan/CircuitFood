package com.johnbuhanan.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.concurrent.ThreadSafeMap
import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.johnbuhanan.features.food.Food.Route.FoodCategories
import com.johnbuhanan.navigation.RouterEvent.Pop
import com.johnbuhanan.navigation.RouterEvent.Push
import com.johnbuhanan.navigation.RouterEvent.PushBottomSheet
import kotlin.reflect.KClass
import timber.log.Timber

private typealias ProviderKey = KClass<out ScreenProvider>
private typealias ScreenFactory = (ScreenProvider) -> Screen

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun NavigatorWrapper() {
    var navigator: Navigator? = null
    var bottomSheetNavigator: BottomSheetNavigator? = null
    val router = hiltViewModel<RouterViewModel>()

    LaunchedEffect(Unit) {
        Timber.e("BEFORE ROUTER")
        router.routerEvents.collect { routerEvent ->
            Timber.e("routerEvent: $routerEvent")
            when (routerEvent) {
                is Pop -> {
                    Timber.e("GoBack")
                    navigator?.pop()
                }
                is Push -> {
                    val screens = routerEvent.routes.map { it.toScreen() }
                    navigator?.push(screens)
                }
                is PushBottomSheet -> {
                    val bottomSheetScreen = routerEvent.route.toScreen()
                    bottomSheetNavigator?.show(bottomSheetScreen)
                }
            }
        }
    }

    val screen = rememberScreen(FoodCategories)
    BottomSheetNavigator(
        scrimColor = MaterialTheme.colors.surface.copy(alpha = 0.32f),
        sheetShape = MaterialTheme.shapes.large.copy(
            topStart = RoundedCornerShape(16.dp).topStart,
            topEnd = RoundedCornerShape(16.dp).topEnd
        ),
    ) { bottomSheetNav ->
        bottomSheetNavigator = bottomSheetNav

        Navigator(screen) { nav ->
            navigator = nav
            SlideTransition(nav)
        }
    }
}

@Suppress("UNCHECKED_CAST")
object ScreenRegistryHelper {
    private val factoriesMember by lazy { ScreenRegistry::class.members.find { it.name == "factories" } }
    private val factories: ThreadSafeMap<ProviderKey, ScreenFactory> by lazy {
        factoriesMember!!.call(ScreenRegistry) as ThreadSafeMap<ProviderKey, ScreenFactory>
    }

    fun get(route: Route): Screen {
        val screenFactory = factories[route::class]!!
        return screenFactory.invoke(route)
    }
}

fun Route.toScreen(): Screen {
    return ScreenRegistryHelper.get(this)
}