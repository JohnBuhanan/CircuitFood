package com.americanexpress.android.testgenerator

import android.content.Context
import android.content.Intent
import com.americanexpress.android.extension.getRequiredExtra
import com.americanexpress.android.rxworkflow.view.WorkflowContext
import com.americanexpress.rxworkflow.components.android.workflow.BaseWorkflowActivity
import com.americanexpress.android.rxworkflow.screen.Screen
import javax.inject.Inject

class TestGeneratorActivity : BaseWorkflowActivity<TestGeneratorInput, TestGeneratorWorkflowResult, Screen<*, *, *>>() {

    @Inject
    lateinit var workflow: TestGeneratorWorkflow

    private val viewFactory = testGeneratorViewFactory

    override val isValidLoggedSessionRequired = true

    override fun getWorkflowContext(): WorkflowContext<TestGeneratorInput, TestGeneratorWorkflowResult, Screen<*, *, *>> {
        return WorkflowContext(
            workflow,
            viewFactory,
            intent.getRequiredExtra(INPUT_EXTRA)
        ) {

        }
    }

    companion object {

        private const val INPUT_EXTRA = "INPUT"

        fun intent(context: Context) = intent(context, TestGeneratorInput)

        private fun intent(context: Context, input: TestGeneratorInput) = Intent(context, TestGeneratorActivity::class.java).putExtra(INPUT_EXTRA, input)
    }
}
