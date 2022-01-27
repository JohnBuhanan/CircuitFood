package com.codingtroops.foodies.ui.feature.category_details

import com.codingtroops.common.UiEffect
import com.codingtroops.common.UiEvent
import com.codingtroops.common.UiState
import com.codingtroops.foodies.model.FoodItem

sealed class FoodCategoryDetailsEvent : UiEvent

data class FoodCategoryDetailsState(
    val category: FoodItem?,
    val categoryFoodItems: List<FoodItem>,
) : UiState

sealed class FoodCategoryDetailsEffect : UiEffect
