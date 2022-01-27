package com.codingtroops.foodies

import app.cash.turbine.test
import com.codingtroops.foodies.model.data.FoodMenuRepository
import com.codingtroops.foodies.ui.feature.categories.FoodCategoriesEvent
import com.codingtroops.foodies.ui.feature.categories.FoodCategoriesViewModel
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val dispatcher = TestCoroutineDispatcher()
    private val repository = object : FoodMenuRepository by stub() {}
    private val foodCategoriesViewModel = FoodCategoriesViewModel(
        dispatcher = dispatcher,
        repository = repository,
    )

    @Test
    fun addition_isCorrect() {
        foodCategoriesViewModel.setEvent(FoodCategoriesEvent.CategorySelection("beef"))

//        foodCategoriesViewModel.effect.test {
//
//        }

        assertEquals(4, 2 + 2)
    }
}