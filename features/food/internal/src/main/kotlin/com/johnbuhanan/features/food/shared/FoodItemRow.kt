package com.johnbuhanan.features.food.shared

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.johnbuhanan.libraries.food.model.FoodItem

/**
 * This is shared between `categories` and `details`.
 */
@Composable
fun FoodItemRow(
    item: FoodItem,
    itemShouldExpand: Boolean = false,
    iconTransformationBuilder: ImageRequest.Builder.() -> Unit = { },
    onItemClicked: (foodItem: FoodItem) -> Unit = { },
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        // TODO Material3 changes color instead of drawing shadow to imply elevation.
        // elevation = CardDefaults.cardElevation(
        //     defaultElevation = 2.dp
        // ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(item) }
    ) {
        var expanded by remember { mutableStateOf(false) }
        Row(modifier = Modifier.animateContentSize()) {
            Box(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                FoodItemThumbnail(item.thumbnailUrl, iconTransformationBuilder)
            }
            FoodItemDetails(
                item = item,
                expandedLines = if (expanded) 10 else 2,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    )
                    .fillMaxWidth(0.80f)
                    .align(Alignment.CenterVertically)
            )
            if (itemShouldExpand)
                Box(
                    modifier = Modifier
                        .align(if (expanded) Alignment.Bottom else Alignment.CenterVertically)
                        .noRippleClickable { expanded = !expanded }
                ) {
                    ExpandableContentIcon(expanded)
                }
        }
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}