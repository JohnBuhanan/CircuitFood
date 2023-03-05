package com.johnbuhanan.features.featureA.screen2

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.viewmodel.WithViewModel
import com.johnbuhanan.features.featureA.screen1.Screen1Event
import com.johnbuhanan.features.featureA.screen1.Screen1ViewModel
import com.johnbuhanan.features.featureA.screen2.Screen2Effect.ShowToast
import com.johnbuhanan.navigation.RouterViewModel
import timber.log.Timber

class Screen2Screen() : AndroidScreen() {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        Timber.e("Composable - FeatureA")
        WithViewModel<Screen2ViewModel>(
            onEffect = { effect ->
                when (effect) {
                    is ShowToast -> {
                        Timber.e("Composable - onEffect - ShowToast")
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                    is Screen2Effect.SetResult -> {
                        val screen1ViewModel: Screen1ViewModel = viewModel()
                        screen1ViewModel.setEvent(Screen1Event.Screen2ResultSet(effect.value))
                        val router: RouterViewModel = viewModel()
                        router.pop()
                    }
                }
            },
            start = { viewModel, onEvent ->
                BackHandler {
                    Timber.e("BACK_HANDLER")
                    onEvent(Screen2Event.TappedBack)
                }
                when (val state = viewModel.state.collectAsState().value) {
                    is Screen2State -> Screen2View(
                        message = state.message,
                        onEvent = onEvent,
                    )
                }
            },
        )
    }
}

