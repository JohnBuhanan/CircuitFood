package com.johnbuhanan.features.food.categories

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import coil.Coil
import com.google.common.truth.Truth.assertThat
import com.johnbuhanan.common.ui.PROGRESS_TAG
import com.johnbuhanan.features.food.categories.FoodCategoriesConstants.FOOD_LIST_TAG
import com.johnbuhanan.libraries.food.model.FoodItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.test.runTest
import leakcanary.DetectLeaksAfterTestSuccess.Companion.detectLeaksAfterTestSuccessWrapping
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class FoodCategoriesTest {
    private val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rule =
        RuleChain.emptyRuleChain()
            .detectLeaksAfterTestSuccessWrapping(tag = "ActivitiesDestroyed") {
                around(composeTestRule)
            }

    @Before
    fun setup() {
        Coil.setImageLoader(FakeImageLoader())
    }

    @Test
    fun foodCategories_show_progress_indicator_for_loading_state() {
        composeTestRule.run {
            setContent { FoodCategoriesView(FoodCategoriesState.Loading) }

            onNodeWithTag(PROGRESS_TAG).assertIsDisplayed()
            onNodeWithTag(FOOD_LIST_TAG).assertDoesNotExist()
        }
    }

    @Test
    fun foodCategories_show_list_for_success_state() {
        composeTestRule.run {
            setContent { FoodCategoriesView(FoodCategoriesState.Success(listOf(FOOD_ITEM), "") {}) }

            onNodeWithTag(PROGRESS_TAG).assertDoesNotExist()

            onAllNodesWithTag(FOOD_LIST_TAG).assertCountEquals(1)
            onNodeWithText(FOOD_ITEM.name).assertIsDisplayed()
            onNodeWithText(FOOD_ITEM.description).assertIsDisplayed()
        }
    }

    @Test
    fun foodCategories_emits_event_when_tapping_on_foodItem() = runTest {
        val channel = Channel<Any>(1)

        composeTestRule.run {
            setContent {
                FoodCategoriesView(
                    FoodCategoriesState.Success(
                        listOf(FOOD_ITEM),
                        "",
                        channel::trySend
                    )
                )
            }

            onAllNodesWithTag(FOOD_LIST_TAG).assertCountEquals(1)[0].performClick()

            val event = channel.receive()
            assertThat(event).isEqualTo(FoodCategoriesEvent.TappedCategory(FOOD_ITEM.id))
        }
    }

    private companion object {
        val FOOD_ITEM =
            FoodItem(
                id = "id",
                name = "name",
                thumbnailUrl = "thumbnailUrl",
                description = "description",
            )
    }
}
