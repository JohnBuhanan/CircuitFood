package com.codingtroops.foodies.ui.feature.categories

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.codingtroops.foodies.R
import com.codingtroops.foodies.model.FoodItem
import com.codingtroops.foodies.noRippleClickable
import com.codingtroops.foodies.ui.feature.categories.FoodCategoriesEffect.DataWasLoaded
import com.codingtroops.foodies.ui.feature.categories.FoodCategoriesEffect.Navigation.ToCategoryDetails
import com.codingtroops.foodies.ui.feature.destinations.FoodCategoryDetailsDestination
import com.codingtroops.foodies.ui.shared.WithViewModel
import com.codingtroops.foodies.ui.theme.ComposeSampleTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber


//suspend fun handleSideEffect(effect: Effect, navigator: DestinationsNavigator, scaffoldState: ScaffoldState) {
//    Timber.e("Composable - handleSideEffect")
//    when (effect) {
//        is FoodCategoriesContract.Effect.DataWasLoaded ->
//            scaffoldState.snackbarHostState.showSnackbar(
//                message = "Food categories are loaded.",
//                duration = SnackbarDuration.Short
//            )
//        is FoodCategoriesContract.Effect.Navigation.ToCategoryDetails -> {
//            Timber.e("Composable - handleSideEffect - ToCategoryDetails")
//            navigator.navigate(FoodCategoryDetailsDestination(effect.categoryName))//effect.categoryName))
//        }
//    }
//}

@Destination(start = true)
@Composable
fun FoodCategories(
    navigator: DestinationsNavigator,
) {
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
                is ToCategoryDetails -> {
                    Timber.e("Composable - onEffect - ToCategoryDetails")
                    navigator.navigate(FoodCategoryDetailsDestination(effect.categoryName))
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
                    onEvent(FoodCategoriesEvent.CategorySelection(itemId))
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
        title = { Text(stringResource(R.string.app_name)) },
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

//val viewModel = FoodCategoriesViewModel(repository = object : FoodMenuRepository {
//    val foodItems = (0..10).map {
//        FoodItem(
//            id = "id$it",
//            name = "name$it",
//            thumbnailUrl = "thumbnailUrl$it",
//        )
//    }
//
//    override suspend fun getFoodCategories(): List<FoodItem> {
//        return foodItems
//    }
//
//    override suspend fun getMealsByCategory(categoryId: String): List<FoodItem> {
//        return foodItems
//    }
//})

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
//        FoodCategoriesView(viewModel)
    }
}