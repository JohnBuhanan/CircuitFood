package com.americanexpress.android.${featurename}

import com.americanexpress.android.rxworkflow.screen.TestWorkflow
import org.junit.Test

class ${FeatureName}WorkflowTest {

    private val testWorkflow = TestWorkflow(${FeatureName}Workflow())

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
