package com.codingtroops.foodies.ui.feature.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codingtroops.foodies.ui.feature.NavGraphs
import com.codingtroops.foodies.ui.theme.ComposeSampleTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
