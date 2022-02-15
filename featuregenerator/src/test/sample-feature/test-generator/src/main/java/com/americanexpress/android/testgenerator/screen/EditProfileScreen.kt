package com.americanexpress.android.testgenerator.screen

import com.americanexpress.android.rxworkflow.screen.BaseScreen
import com.americanexpress.android.testgenerator.screen.EditProfileState.InitialState

@Suppress("NotImplementedDeclaration") // TODO remove during implementation!!!
class EditProfileScreen: BaseScreen<EditProfileState, EditProfileCommand, EditProfileEffect>() {

    override fun onAttach(): Next<EditProfileState, EditProfileCommand, EditProfileEffect> {
        return Next(state = InitialState(title = "EditProfile"))
    }

    override fun onCommand(command: EditProfileCommand, state: EditProfileState): Next<EditProfileState, EditProfileCommand, EditProfileEffect> {
        TODO("not implemented")
    }

}

sealed class EditProfileState {
    data class InitialState(val title: String) : EditProfileState()
}

sealed class EditProfileCommand

sealed class EditProfileEffect
