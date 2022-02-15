package com.johnbuhanan.features.${featurename}.view

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
import com.johnbuhanan.features.food.categories.${ScreenName}Event.TappedCategory
import com.johnbuhanan.features.food.domain.FoodItem
import com.johnbuhanan.features.food.shared.FoodItemRow

@Preview
@Composable
fun ${ScreenName}View(
    categories: List<FoodItem> = emptyList(),
    isLoading: Boolean = false,
    onEvent: (${ScreenName}Event) -> Unit = {},
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CategoriesAppBar()
        },
    ) {
        Box {
            ${ScreenName}List(
                foodItems = categories,
                onItemClicked = { itemId ->
                    onEvent(TappedCategory(itemId))
                },
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
        title = { Text("stringResource(R.string.app_name)") },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun ${ScreenName}List(
    foodItems: List<FoodItem>,
    onItemClicked: (id: String) -> Unit = { },
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(foodItems) { item ->
            FoodItemRow(item = item, itemShouldExpand = true, onItemClicked = onItemClicked)
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
