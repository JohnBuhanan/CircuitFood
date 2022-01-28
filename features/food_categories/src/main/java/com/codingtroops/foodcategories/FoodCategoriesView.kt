package com.codingtroops.foodcategories

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration.Short
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.codingtroops.common.WithViewModel
import com.codingtroops.common.noRippleClickable
import com.codingtroops.foodcategories.FoodCategoriesEffect.DataWasLoaded
import com.codingtroops.foodcategories.FoodCategoriesEvent.CategorySelection
import com.codingtroops.networking.FoodItem
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination(start = true)
@Composable
fun FoodCategories() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    WithViewModel<FoodCategoriesViewModel>(
        onEffect = { effect ->
            Timber.e("Composable - onEffect")
            when (effect) {
                is DataWasLoaded -> {
                    Timber.e("Composable - onEffect - DataWasLoaded")
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Food categories are loaded.",
                        duration = Short
                    )
                }
            }
        },
        start = { viewModel, onEvent ->
            Timber.e("FoodCategoriesView - start")
            val state = viewModel.state.value
            FoodCategoriesView(state.categories, state.isLoading, onEvent)
        }
    )
}

@Composable
fun FoodCategoriesView(
    categories: List<FoodItem>,
    isLoading: Boolean,
    onEvent: (FoodCategoriesEvent) -> Unit,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CategoriesAppBar()
        },
    ) {
        Box {
            FoodCategoriesList(
                foodItems = categories,
                onItemClicked = { itemId ->
                    onEvent(CategorySelection(itemId))
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
fun FoodCategoriesList(
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

@Composable
fun FoodItemRow(
    item: FoodItem,
    itemShouldExpand: Boolean = false,
    iconTransformationBuilder: ImageRequest.Builder.() -> Unit = { },
    onItemClicked: (id: String) -> Unit = { },
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(item.id) }
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

@Composable
private fun ExpandableContentIcon(expanded: Boolean) {
    Icon(
        imageVector = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown,
        contentDescription = "Expand row icon",
        modifier = Modifier
            .padding(all = 16.dp)
    )
}

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
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        if (item?.description?.trim()?.isNotEmpty() == true)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = item.description.trim(),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.caption,
                    maxLines = expandedLines
                )
            }
    }
}

//@Preview
//@Composable
//fun PreviewFoodItemThumbnail() {
//    FoodItemThumbnail(materialIcon(),
//        iconTransformationBuilder = {
//            transformations(
//                CircleCropTransformation()
//            )
//        }
//    )
//}

@Composable
fun FoodItemThumbnail(
    thumbnailUrl: Any?,
    iconTransformationBuilder: ImageRequest.Builder.() -> Unit,
) {
    Image(
        painter = rememberImagePainter(
            data = thumbnailUrl,
            builder = iconTransformationBuilder
        ),
        modifier = Modifier
            .size(88.dp)
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
        contentDescription = "Food item thumbnail picture",
    )
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
