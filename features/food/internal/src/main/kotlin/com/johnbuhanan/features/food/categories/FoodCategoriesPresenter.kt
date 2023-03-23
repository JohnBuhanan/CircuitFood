package com.johnbuhanan.features.food.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.johnbuhanan.features.food.FoodCategoryDetailsScreen
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItems
import com.slack.circuit.Navigator
import com.slack.circuit.Presenter
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject
import javax.inject.Singleton

@Stable
data class DetailsResult(val value: String)

//interface Bridge {
//    fun setResult(result: Any)
////    fun onResult(lambda: (Any) -> Unit)
//}

@Singleton
//@ContributesBinding(AppScope::class)
class BridgeImpl @Inject constructor() { // : Bridge {
    val map = mutableMapOf<String, State<*>>()

    //    val test = mutableStateOf()
//    private val map = mutableStateMapOf<Any, Any>()
//    private lateinit var _onResult: (State<Any>) -> Unit
//    private val channel = Channel<Any>()
//    private val mutableSharedFlow = MutableSharedFlow<Any>()
    private val mutableState = mutableStateOf<String?>(null)

    //    private val mutableStateFlow: MutableStateFlow<Any?> = MutableStateFlow(null)
//    private lateinit var _lambda: (Any) -> Unit
    fun setResult(result: String) {
//        _lambda(result)
//        mutableStateFlow.value = result
//        channel.trySend(result)
//        mutableSharedFlow.tryEmit(result)
//        mutableSharedFlow.emit(result)
        mutableState.value = result
    }


    //    fun onResult(lambda: (Any) -> Unit) {
//        _lambda = lambda
//    }
    fun onResult(): MutableState<String?> {
//        val state = map.
//    fun onResult(): Flow<Any?> {
//        return channel.receiveAsFlow()
//        return mutableStateFlow
        return mutableState
    }
}

//inline fun <reified T> BridgeImpl.onResult(): MutableState<T> {
//    map.putIfAbsent(T::class.java.name, mutableStateOf(null))
//    val state = map[T::class.java.name]
//
//    return state as MutableState<T>
//}

//inline fun <reified T> Bridge.listenFor(function: () -> Unit) {
//
//}

class FoodCategoriesPresenter @AssistedInject constructor(
    private val getFoodCategoriesAsItems: GetFoodCategoriesAsItems,
    private val bridge: BridgeImpl,
    @Assisted private val navigator: Navigator,
) : Presenter<FoodCategoriesState> {
    @Composable
    override fun present(): FoodCategoriesState {
        var isLoading by remember { mutableStateOf(true) }
        val categories by produceState<List<FoodItem>>(emptyList(), null) {
            isLoading = true
            getFoodCategoriesAsItems().fold(
                {
                    isLoading = false
                    value = it
                },
                {

                }
            )
        }


//        rememberLauncherForActivityResult(contract =, onResult =)
//        var resultText by remember { mutableStateOf("") }
//        val detailsResult: DetailsResult? by remember { bridge.onResult() }
        val resultText by remember { bridge.onResult() }

//        rememberUpdatedState(bridge.onResult {
//            resultText = it.toString()
//        })
//        LaunchedEffect(null) {
//             { result ->
//                result?.let {
//                    resultText = it.toString()
//                }
//            }
//        }

        return FoodCategoriesState(
            categories = categories,
            isLoading = isLoading,
            resultText = resultText ?: "",
        ) { event ->
            when (event) {
                is FoodCategoriesEvent.TappedCategory -> {
                    navigator.goTo(FoodCategoryDetailsScreen(event.id))
                }
            }
        }
    }

    @CircuitInject(FoodCategoriesScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): FoodCategoriesPresenter
    }
}