package com.johnbuhanan.features.food.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.johnbuhanan.features.food.details.FoodCategoryDetailsEvent.TappedFoodItem
import com.johnbuhanan.features.food.domain.FoodItem
import com.johnbuhanan.features.food.shared.FoodItemDetails
import com.johnbuhanan.features.food.shared.FoodItemRow
import timber.log.Timber
import kotlin.math.min

@Composable
fun FoodCategoryDetailsView(
    category: FoodItem?,
    categoryFoodItems: List<FoodItem>,
    onEvent: (FoodCategoryDetailsEvent) -> Unit = {},
) {
    Timber.e("Composable - FoodCategoryDetailsView")
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Surface(elevation = 4.dp) {
                CategoryDetailsCollapsingToolbar(category, scrollOffset)
            }
            Spacer(modifier = Modifier.height(2.dp))
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(categoryFoodItems) { item ->
                    FoodItemRow(
                        item = item,
                        iconTransformationBuilder = {
                            transformations(
                                CircleCropTransformation()
                            )
                        },
                        onItemClicked = { onEvent(TappedFoodItem("${item.name} was tapped!!!")) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun CategoryDetailsCollapsingToolbar(
    category: FoodItem?,
    scrollOffset: Float,
) {
    val imageSize by animateDpAsState(targetValue = max(72.dp, 128.dp * scrollOffset))
    Row {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = CircleShape,
            border = BorderStroke(
                width = 2.dp,
                color = Color.Black
            ),
            elevation = 4.dp
        ) {
            Image(
                painter = rememberImagePainter(
                    data = category?.thumbnailUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    },
                ),
                modifier = Modifier.size(max(72.dp, imageSize)),
                contentDescription = "Food category thumbnail picture",
            )
        }
        FoodItemDetails(
            item = category,
            expandedLines = (kotlin.math.max(3f, scrollOffset * 6)).toInt(),
            modifier = Modifier
                .padding(
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
        )
    }
}
