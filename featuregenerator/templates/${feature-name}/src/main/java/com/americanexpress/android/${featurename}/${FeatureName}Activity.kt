package com.americanexpress.android.${featurename}

import android.content.Context
import android.content.Intent
import com.americanexpress.android.extension.getRequiredExtra
import com.americanexpress.android.rxworkflow.view.WorkflowContext
import com.americanexpress.rxworkflow.components.android.workflow.BaseWorkflowActivity
import com.americanexpress.android.rxworkflow.screen.Screen
import javax.inject.Inject

class ${FeatureName}Activity : BaseWorkflowActivity<${FeatureName}Input, ${FeatureName}WorkflowResult, Screen<*, *, *>>() {

    @Inject
    lateinit var workflow: ${FeatureName}Workflow

    private val viewFactory = ${featureName}ViewFactory

    override val isValidLoggedSessionRequired = true

    override fun getWorkflowContext(): WorkflowContext<${FeatureName}Input, ${FeatureName}WorkflowResult, Screen<*, *, *>> {
        return WorkflowContext(
            workflow,
            viewFactory,
            intent.getRequiredExtra(INPUT_EXTRA)
        ) {

        }
    }

    companion object {

        private const val INPUT_EXTRA = "INPUT"

        fun intent(context: Context) = intent(context, ${FeatureName}Input)

        private fun intent(context: Context, input: ${FeatureName}Input) = Intent(context, ${FeatureName}Activity::class.java).putExtra(INPUT_EXTRA, input)
    }
}
