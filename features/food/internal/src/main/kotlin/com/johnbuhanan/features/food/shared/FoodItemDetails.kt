package com.johnbuhanan.features.food.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.johnbuhanan.libraries.food.model.FoodItem

@Composable
fun FoodItemDetails(
    item: FoodItem?,
    expandedLines: Int,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = item?.name ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        if (item?.description?.trim()?.isNotEmpty() == true)
            Text(
                text = item.description.trim(),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                maxLines = expandedLines
            )
    }
}
