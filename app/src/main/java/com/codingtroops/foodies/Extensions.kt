package com.codingtroops.foodies

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.repeatOnLifecycle
import com.codingtroops.foodies.base.BaseViewModel
import com.codingtroops.foodies.base.UiEffect
import com.codingtroops.foodies.base.UiEvent
import com.codingtroops.foodies.base.UiState
import kotlinx.coroutines.flow.collect
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun <EVENT : UiEvent, STATE : UiState, EFFECT : UiEffect> BaseViewModel<EVENT, STATE, EFFECT>.collectEffect(
    doEffect: (suspend (effect: EFFECT) -> Unit),
) {
    val effectFlow = this.effect
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(effectFlow, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            effect.collect { doEffect(it) }
        }
    }
}

class SavedStateHandleDelegate<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String,
    defaultValue: T,
) : ReadWriteProperty<Any, T> {
    private val state: MutableState<T>

    init {
        val savedValue = savedStateHandle.get<T>(key)
        state = mutableStateOf(
            savedValue ?: defaultValue
        )
    }

    override fun getValue(thisRef: Any, property: KProperty<*>) = state.value

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        state.value = value
        savedStateHandle.set(key, value)
    }
}

fun <T> SavedStateHandle.mutableStateOf(
    defaultValue: T,
) = PropertyDelegateProvider<Any, SavedStateHandleDelegate<T>> { _, property ->
    SavedStateHandleDelegate(
        savedStateHandle = this,
        key = property.name,
        defaultValue = defaultValue,
    )
}

///**
// * Observe [Container.stateFlow] in a Compose [LaunchedEffect].
// */
//@SuppressLint("ComposableNaming")
//@Composable
//fun <EVENT : UiEvent, STATE : UiState, EFFECT : UiEffect> BaseViewModel<EVENT, STATE, EFFECT>.collectState(
//    doState: (suspend (state: STATE) -> Unit),
//) {
//    val stateFlow = this.state
//    val lifecycleOwner = LocalLifecycleOwner.current
//    LaunchedEffect(stateFlow, lifecycleOwner) {
//        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//            state.collect { doState(it) }
//        }
//    }
//}


///**
// * Observe [Container.stateFlow] as [State].
// */
//@Composable
//public fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectAsState(): State<STATE> =
//    container.stateFlow.collectAsState()