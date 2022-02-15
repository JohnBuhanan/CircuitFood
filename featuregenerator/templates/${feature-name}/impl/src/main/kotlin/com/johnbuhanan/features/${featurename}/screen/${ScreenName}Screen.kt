package com.johnbuhanan.features.${featurename}.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.androidx.AndroidScreen
import com.johnbuhanan.common.viewmodel.WithViewModel
import com.johnbuhanan.features.${featurename}.view.${ScreeName}View
import com.johnbuhanan.features.${featurename}.viewmodel.${ScreeName}Effect.ShowToast
import com.johnbuhanan.features.${featurename}.viewmodel.${ScreeName}ViewModel

class ${ScreenName}Screen : AndroidScreen() {
    @Composable
    override fun Content() {
        val context = LocalContext.current

        WithViewModel<${ScreenName}ViewModel>(
            onEffect = { effect ->
                when (effect) {
                    is ShowToast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            start = { viewModel, onEvent ->
                val state = viewModel.state.collectAsState().value
                ${ScreenName}View(state.isLoading, onEvent)
            }
        )
    }
}
