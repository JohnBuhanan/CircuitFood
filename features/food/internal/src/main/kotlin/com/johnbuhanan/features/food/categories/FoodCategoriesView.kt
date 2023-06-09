@file:OptIn(ExperimentalMaterial3Api::class)

package com.johnbuhanan.features.food.categories

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.ui.AppBar
import com.johnbuhanan.common.ui.LoadingBar
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.johnbuhanan.features.food.categories.FoodCategoriesConstants.FOOD_LIST_TAG
import com.johnbuhanan.features.food.shared.FoodItemRow
import com.johnbuhanan.libraries.food.model.FoodItem
import com.slack.circuit.codegen.annotations.CircuitInject

internal object FoodCategoriesConstants {
    const val FOOD_LIST_TAG = "grid"
}


@CircuitInject(FoodCategoriesScreen::class, AppScope::class)
@Composable
fun FoodCategoriesView(
    foodCategoriesState: FoodCategoriesState,
    modifier: Modifier = Modifier,
) {
    when (foodCategoriesState) {
        is FoodCategoriesState.Loading -> LoadingBar()
        is FoodCategoriesState.Success -> ShowFoodCategories(foodCategoriesState, modifier)
    }
}

@Composable
fun ShowFoodCategories(foodCategoriesState: FoodCategoriesState.Success, modifier: Modifier) {
    BackHandler {
        foodCategoriesState.eventSink(FoodCategoriesEvent.TappedBack)
    }
    Scaffold(
        topBar = {
            AppBar()
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text(foodCategoriesState.resultText)
            FoodItemList(
                foodItems = foodCategoriesState.categories,
                onEvent = foodCategoriesState.eventSink,
            )
        }
    }
}

@Composable
fun FoodItemList(
    foodItems: List<FoodItem>,
    onEvent: (FoodCategoriesEvent) -> Unit = {},
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.testTag(FOOD_LIST_TAG),
    ) {
        items(foodItems) { item ->
            FoodItemRow(
                item = item,
                itemShouldExpand = true,
                onItemClicked = { onEvent(FoodCategoriesEvent.TappedCategory(it.id)) })
        }
    }
}
