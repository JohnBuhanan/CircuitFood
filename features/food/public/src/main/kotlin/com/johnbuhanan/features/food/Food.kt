package com.johnbuhanan.features.food

import com.slack.circuit.Screen
import kotlinx.parcelize.Parcelize


//interface Food {
//    object FoodScreenInput
//    object FoodScreenOutput
//    sealed class Route : ScreenProvider, Serializable {
//        object FoodCategories : Route()
//        data class FoodCategoryDetails(val id: String) : Route(), java.io.Serializable
//    }
//}
@Parcelize
object CounterScreen : Screen {}