//package com.johnbuhanan.features.food.categories
//
//import android.widget.Toast
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.platform.LocalContext
//import cafe.adriel.voyager.androidx.AndroidScreen
//import com.johnbuhanan.common.viewmodel.WithViewModel
//import com.johnbuhanan.features.food.categories.FoodCategoriesEffect.ShowToast
//import timber.log.Timber
//
//class FoodCategoriesScreen : AndroidScreen() {
//    @Composable
//    override fun Content() {
//        val context = LocalContext.current
//
//        WithViewModel<FoodCategoriesViewModel>(
//            onEffect = { effect ->
//                Timber.e("Composable - onEffect")
//                when (effect) {
//                    is ShowToast -> {
//                        Timber.e("Composable - onEffect - ShowToast")
//                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            },
//            start = { viewModel, onEvent ->
//                Timber.e("FoodCategoriesView - start")
//                val state = viewModel.state.collectAsState().value
//                FoodCategoriesView(state.categories, state.isLoading, onEvent)
//            }
//        )
//    }
//}
