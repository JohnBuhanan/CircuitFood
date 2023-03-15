//package com.johnbuhanan.features.food.details
//
//import android.widget.Toast
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.platform.LocalContext
//import cafe.adriel.voyager.androidx.AndroidScreen
//import com.johnbuhanan.common.viewmodel.WithViewModel
//
//data class FoodCategoryDetailsScreen(val id: String) : AndroidScreen() {
//    @Composable
//    override fun Content() {
//        val context = LocalContext.current
//        WithViewModel<FoodCategoryDetailsViewModel>(
//            onEffect = { effect ->
//                when (effect) {
//                    is FoodCategoryDetailsEffect.ShowToast -> {
//                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            },
//            start = { viewModel, onEvent ->
//                when (val state = viewModel.state.collectAsState().value) {
//                    is FoodCategoryDetailsState -> FoodCategoryDetailsView(
//                        category = state.category,
//                        categoryFoodItems = state.foodItems,
//                        onEvent = onEvent
//                    )
//                }
//            },
//        )
//    }
//}
