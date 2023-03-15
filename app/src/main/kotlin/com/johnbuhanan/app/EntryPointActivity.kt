package com.johnbuhanan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.johnbuhanan.features.food.CounterScreen
import com.slack.circuit.CircuitConfig
import com.slack.circuit.CircuitContent
import javax.inject.Inject

// Single Activity per app
class EntryPointActivity : ComponentActivity() {

    @Inject
    lateinit var circuitConfig: CircuitConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.create().inject(this)

        setContent {
            CircuitContent(
                circuitConfig = circuitConfig,
                screen = CounterScreen,
            )
        }
    }
}
