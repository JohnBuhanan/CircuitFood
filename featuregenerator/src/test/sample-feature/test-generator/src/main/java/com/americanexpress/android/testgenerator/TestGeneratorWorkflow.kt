package com.americanexpress.android.testgenerator

import javax.inject.Inject
import com.americanexpress.android.rxworkflow.workflow.BaseWorkflow
import java.io.Serializable

class TestGeneratorWorkflow @Inject constructor(

) : BaseWorkflow<TestGeneratorInput, TestGeneratorWorkflowResult>() {

    override fun onInputReceived(input: TestGeneratorInput) {
        error("not implemented")
    }

}

object TestGeneratorInput : Serializable

sealed class TestGeneratorWorkflowResult
