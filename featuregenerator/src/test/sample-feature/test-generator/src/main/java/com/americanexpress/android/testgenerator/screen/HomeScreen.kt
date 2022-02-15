package com.americanexpress.android.testgenerator.screen

import com.americanexpress.android.rxworkflow.screen.BaseScreen
import com.americanexpress.android.testgenerator.screen.HomeState.InitialState

@Suppress("NotImplementedDeclaration") // TODO remove during implementation!!!
class HomeScreen: BaseScreen<HomeState, HomeCommand, HomeEffect>() {

    override fun onAttach(): Next<HomeState, HomeCommand, HomeEffect> {
        return Next(state = InitialState(title = "Home"))
    }

    override fun onCommand(command: HomeCommand, state: HomeState): Next<HomeState, HomeCommand, HomeEffect> {
        TODO("not implemented")
    }

}

sealed class HomeState {
    data class InitialState(val title: String) : HomeState()
}

sealed class HomeCommand

sealed class HomeEffect
