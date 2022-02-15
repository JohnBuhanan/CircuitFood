package com.americanexpress.android.${featurename}.screen

import com.americanexpress.android.rxworkflow.screen.BaseScreen
import com.americanexpress.android.${featurename}.screen.${ScreenName}State.InitialState

@Suppress("NotImplementedDeclaration") // TODO remove during implementation!!!
class ${ScreenName}Screen: BaseScreen<${ScreenName}State, ${ScreenName}Command, ${ScreenName}Effect>() {

    override fun onAttach(): Next<${ScreenName}State, ${ScreenName}Command, ${ScreenName}Effect> {
        return Next(state = InitialState(title = "${ScreenName}"))
    }

    override fun onCommand(command: ${ScreenName}Command, state: ${ScreenName}State): Next<${ScreenName}State, ${ScreenName}Command, ${ScreenName}Effect> {
        TODO("not implemented")
    }

}

sealed class ${ScreenName}State {
    data class InitialState(val title: String) : ${ScreenName}State()
}

sealed class ${ScreenName}Command

sealed class ${ScreenName}Effect
