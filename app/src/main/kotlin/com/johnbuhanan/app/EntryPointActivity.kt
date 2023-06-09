package com.johnbuhanan.app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.johnbuhanan.app.theme.ComposeSampleTheme
import com.johnbuhanan.common.di.ActivityKey
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, boundType = Activity::class)
@ActivityKey(EntryPointActivity::class)
class EntryPointActivity @Inject constructor(
    private val circuitConfig: CircuitConfig
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val backstack = rememberSaveableBackStack { push(FoodCategoriesScreen) }
            val circuitNavigator = rememberCircuitNavigator(backstack)
            val items = remember { listOf(BottomNavItem.FoodNavItem, BottomNavItem.AboutNavItem) }
            var selectedIndex by rememberSaveable { mutableStateOf(0) }

            ComposeSampleTheme {
                Scaffold(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .systemBarsPadding()
                        .fillMaxWidth(),
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, bottomNavItem ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = bottomNavItem.icon,
                                            contentDescription = bottomNavItem.title
                                        )
                                    },
                                    label = { Text(text = bottomNavItem.title) },
                                    alwaysShowLabel = true,
                                    selected = index == selectedIndex,
                                    onClick = {
                                        selectedIndex = index
                                        circuitNavigator.resetRoot(bottomNavItem.screen)
                                    }
                                )
                            }
                        }
                    }
                ) {
                    NavigableCircuitContent(
                        navigator = circuitNavigator,
                        backstack = backstack,
                        circuitConfig = circuitConfig,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}
