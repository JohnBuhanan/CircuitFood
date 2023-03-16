package com.johnbuhanan.features.food.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.ui.AppBar
import com.johnbuhanan.common.ui.LoadingBar
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.johnbuhanan.features.food.shared.FoodItemRow
import com.johnbuhanan.libraries.food.model.FoodItem
import com.slack.circuit.codegen.annotations.CircuitInject

@CircuitInject(FoodCategoriesScreen::class, AppScope::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FoodCategoriesView(
    foodCategoriesState: FoodCategoriesState,
    modifier: Modifier,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar()
        },
    ) {
        Box {
            FoodItemList(
                foodItems = foodCategoriesState.categories,
                onEvent = foodCategoriesState.eventSink,
            )
            if (foodCategoriesState.isLoading)
                LoadingBar()
        }
    }
}

@Composable
fun FoodItemList(
    foodItems: List<FoodItem>,
    onEvent: (FoodCategoriesEvent) -> Unit = {},
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(foodItems) { item ->
            FoodItemRow(
                item = item,
                itemShouldExpand = true,
                onItemClicked = { onEvent(FoodCategoriesEvent.TappedCategory(it.id)) })
        }
    }
}
