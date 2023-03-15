package com.johnbuhanan.features.food.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.johnbuhanan.common.di.AppScope
import com.slack.circuit.CircuitUiEvent
import com.slack.circuit.CircuitUiState
import com.slack.circuit.Presenter
import com.slack.circuit.Screen
import com.slack.circuit.codegen.annotations.CircuitInject
import javax.inject.Inject
import kotlinx.parcelize.Parcelize

@Parcelize
object CounterScreen : Screen {
    data class CounterState(
        val count: Int,
        val eventSink: (CounterEvent) -> Unit,
    ) : CircuitUiState

    sealed interface CounterEvent : CircuitUiEvent {
        object Increment : CounterEvent
        object Decrement : CounterEvent
    }
}

@CircuitInject(CounterScreen::class, AppScope::class)
class CounterPresenter @Inject constructor() : Presenter<CounterScreen.CounterState> {
    @Composable
    override fun present(): CounterScreen.CounterState {
        var count by remember { mutableStateOf(0) }

        return CounterScreen.CounterState(count) { event ->
            when (event) {
                is CounterScreen.CounterEvent.Increment -> count++
                is CounterScreen.CounterEvent.Decrement -> count--
            }
        }
    }
}

@CircuitInject(CounterScreen::class, AppScope::class)
@Composable
fun Counter(state: CounterScreen.CounterState, modifier: Modifier) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = "Count: ${state.count}",
//                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            val eventSink = state.eventSink
            Button(
                modifier = Modifier.align(CenterHorizontally),
                onClick = { eventSink(CounterScreen.CounterEvent.Increment) }
            ) { Icon(rememberVectorPainter(Icons.Filled.Add), "Increment") }
            Button(
                modifier = Modifier.align(CenterHorizontally),
                onClick = { eventSink(CounterScreen.CounterEvent.Decrement) }
            ) { Icon(rememberVectorPainter(Icons.Filled.AddCircle), "Decrement") }
        }
    }
}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Preview
//@Composable
//fun FoodCategoriesView(
//    foodItems: List<FoodItem> = emptyList(),
//    isLoading: Boolean = false,
//    onEvent: (FoodCategoriesEvent) -> Unit = {},
//) {
//    val scaffoldState: ScaffoldState = rememberScaffoldState()
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            AppBar()
//        },
//    ) {
//        Box {
//            FoodItemList(
//                foodItems = foodItems,
//                onEvent = onEvent,
//            )
//            if (isLoading)
//                LoadingBar()
//        }
//    }
//}
//
//@Composable
//fun FoodItemList(
//    foodItems: List<FoodItem>,
//    onEvent: (FoodCategoriesEvent) -> Unit = {},
//) {
//    LazyColumn(
//        contentPadding = PaddingValues(bottom = 16.dp)
//    ) {
//        items(foodItems) { item ->
//            FoodItemRow(
//                item = item,
//                itemShouldExpand = true,
//                onItemClicked = { onEvent(TappedCategory(it.id)) })
//        }
//    }
//}
