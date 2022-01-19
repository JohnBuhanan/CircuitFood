package com.codingtroops.foodies.ui.feature.category_details

import com.codingtroops.foodies.base.UiEffect
import com.codingtroops.foodies.base.UiEvent
import com.codingtroops.foodies.base.UiState
import com.codingtroops.foodies.model.FoodItem

sealed class FoodCategoryDetailsEvent : UiEvent

data class FoodCategoryDetailsState(
    val category: FoodItem?,
    val categoryFoodItems: List<FoodItem>,
) : UiState

sealed class FoodCategoryDetailsEffect : UiEffect
