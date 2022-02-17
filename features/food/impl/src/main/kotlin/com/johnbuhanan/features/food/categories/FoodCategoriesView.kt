package com.johnbuhanan.features.food.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.johnbuhanan.common.ui.AppBar
import com.johnbuhanan.common.ui.LoadingBar
import com.johnbuhanan.features.food.categories.FoodCategoriesEvent.TappedCategory
import com.johnbuhanan.features.food.domain.model.FoodItem
import com.johnbuhanan.features.food.shared.FoodItemRow

@Preview
@Composable
fun FoodCategoriesView(
    foodItems: List<FoodItem> = emptyList(),
    isLoading: Boolean = false,
    onEvent: (FoodCategoriesEvent) -> Unit = {},
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
                foodItems = foodItems,
                onEvent = onEvent,
            )
            if (isLoading)
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
            FoodItemRow(item = item, itemShouldExpand = true, onItemClicked = { onEvent(TappedCategory(it.id)) })
        }
    }
}
