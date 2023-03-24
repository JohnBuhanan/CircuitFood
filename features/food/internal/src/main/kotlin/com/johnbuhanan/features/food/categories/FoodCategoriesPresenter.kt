package com.johnbuhanan.features.food.categories

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.features.food.FoodCategoriesScreen
import com.johnbuhanan.features.food.FoodCategoryDetailsScreen
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItems
import com.slack.circuit.Navigator
import com.slack.circuit.Presenter
import com.slack.circuit.codegen.annotations.CircuitInject
import com.squareup.anvil.annotations.ContributesBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class DetailsResult(val value: String) : Parcelable

interface ResultListener {
    val map: MutableMap<String, MutableState<*>>
}

@Singleton
@ContributesBinding(AppScope::class)
class ResultListenerImpl @Inject constructor() : ResultListener {
    override val map: MutableMap<String, MutableState<*>> = mutableMapOf()
}

inline fun <reified T> ResultListener.setResult(result: T) {
    val state: MutableState<T> = map[T::class.java.name]!! as MutableState<T>
    state.value = result
}

inline fun <reified T> ResultListener.onResult(default: T): MutableState<T> {
    map.putIfAbsent(T::class.java.name, mutableStateOf(default))
    val state = map[T::class.java.name]

    return state as MutableState<T>
}

class FoodCategoriesPresenter @AssistedInject constructor(
    private val getFoodCategoriesAsItems: GetFoodCategoriesAsItems,
    private val resultListener: ResultListener,
    @Assisted private val navigator: Navigator,
) : Presenter<FoodCategoriesState> {
    @Composable
    override fun present(): FoodCategoriesState {
        var isLoading by rememberSaveable { mutableStateOf(true) }
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

        val resultText by rememberSaveable { resultListener.onResult<DetailsResult?>(null) }

        return FoodCategoriesState(
            categories = categories,
            isLoading = isLoading,
            resultText = resultText?.value ?: "",
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