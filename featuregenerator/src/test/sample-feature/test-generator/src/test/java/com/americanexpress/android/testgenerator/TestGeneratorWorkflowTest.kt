package com.americanexpress.android.testgenerator

import com.americanexpress.android.rxworkflow.screen.TestWorkflow
import org.junit.Test

class TestGeneratorWorkflowTest {

    private val testWorkflow = TestWorkflow(TestGeneratorWorkflow())

    @Test
    fun `verify Screen1 emits Effect1 and then goes to Screen2`() {
        testWorkflow.apply {
            // onScreen(Screen1::class.java) { Effect1(mock()) }
            // onScreen(Screen2::class.java) {
            //    assertSame(it.arg1, "test")

            //    Effect2(mock(), mock())
            //}
        }
    }
}
