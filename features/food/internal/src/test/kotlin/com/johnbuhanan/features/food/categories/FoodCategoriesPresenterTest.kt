package com.johnbuhanan.features.food.categories

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.johnbuhanan.common.resultlistener.ResultListenerImpl
import com.johnbuhanan.features.food.FoodCategoryDetailsScreen
import com.johnbuhanan.libraries.food.model.FoodItem
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItems
import com.slack.circuit.test.FakeNavigator
import com.slack.circuit.test.test
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

class FakeGetFoodCategoriesAsItems : GetFoodCategoriesAsItems {
    override suspend fun invoke(): Result<List<FoodItem>> {
        return Result.success(listOf(FoodItem("", "", "")))
    }
}

@RunWith(RobolectricTestRunner::class)
class FoodCategoriesPresenterTest {

    private val fakeNavigator = FakeNavigator()
    private val getFoodCategoriesAsItems = FakeGetFoodCategoriesAsItems()
    private val resultListener = ResultListenerImpl()
    private val presenter = FoodCategoriesPresenter(
        getFoodCategoriesAsItems = getFoodCategoriesAsItems,
        resultListener = resultListener,
        navigator = fakeNavigator,
    )

    @Test
    fun `data loads and then clicking item navigates to details screen`() = runTest {
        presenter.test {
            val firstState = awaitItem()
            assertThat(firstState.categories).isEqualTo(listOf())
            assertThat(firstState.isLoading).isEqualTo(true)

            val secondState = awaitItem()
            assertThat(secondState.categories).isEqualTo(listOf(FoodItem("", "", "")))
            assertThat(secondState.isLoading).isEqualTo(false)

            // Clicking the same index does nothing.
            firstState.eventSink(FoodCategoriesEvent.TappedCategory("1"))
            val nextScreen = fakeNavigator.awaitNextScreen()

            assertThat(nextScreen).isEqualTo(FoodCategoryDetailsScreen("1"))
        }
    }
}