package com.americanexpress.android.${featurename}

import javax.inject.Inject
import com.americanexpress.android.rxworkflow.workflow.BaseWorkflow
import java.io.Serializable

class ${FeatureName}Workflow @Inject constructor(

) : BaseWorkflow<${FeatureName}Input, ${FeatureName}WorkflowResult>() {

    override fun onInputReceived(input: ${FeatureName}Input) {
        error("not implemented")
    }

}

object ${FeatureName}Input : Serializable

sealed class ${FeatureName}WorkflowResult
