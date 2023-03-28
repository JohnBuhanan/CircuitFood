package com.johnbuhanan.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.johnbuhanan.app.theme.ComposeSampleTheme
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.slack.circuit.CircuitConfig
import com.slack.circuit.NavigableCircuitContent
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.push
import com.slack.circuit.rememberCircuitNavigator
import javax.inject.Inject

// Single Activity per app
class EntryPointActivity : ComponentActivity() {

    @Inject
    lateinit var circuitConfig: CircuitConfig

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.create().inject(this)

        setContent {
            val backstack = rememberSaveableBackStack { push(FoodCategoriesScreen) }
            val circuitNavigator = rememberCircuitNavigator(backstack)

            ComposeSampleTheme {
//                Scaffold(
//                    modifier = Modifier
//                        .navigationBarsPadding()
//                        .systemBarsPadding()
//                        .fillMaxWidth(),
//                    bottomBar = {
//                        NavigationBar(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer,
//                        ) {
//                            NavigationBarItem(
//                                icon = {
//                                    Icon(
//                                        imageVector = Icons.Filled.Home,
//                                        contentDescription = "title"
//                                    )
//                                },
//                                label = { Text(text = "Label") },
//                                alwaysShowLabel = true,
//                                selected = false,
//                                onClick = { }
//                            )
//                        }
//                    }
//                ) {
                NavigableCircuitContent(
                    navigator = circuitNavigator,
                    backstack = backstack,
                    circuitConfig = circuitConfig,
                )
            }
        }
    }
//    }
}
