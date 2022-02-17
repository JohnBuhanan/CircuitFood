package com.johnbuhanan.features.food.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            CategoriesAppBar()
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
private fun CategoriesAppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription = "Action icon"
            )
        },
        title = { Text("Tinder Interview") },
        backgroundColor = MaterialTheme.colors.background
    )
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

@Preview
@Composable
fun PreviewLoadingBar() {
    LoadingBar()
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}
