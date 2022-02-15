package com.americanexpress.android.testgenerator.ui

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.americanexpress.android.rxworkflow.view.userInterface
import com.americanexpress.android.rxworkflow.view.attachView
import com.americanexpress.android.rxworkflow.view.OnBackPressed
import com.americanexpress.android.testgenerator.screen.EditProfileState
import com.americanexpress.android.testgenerator.screen.EditProfileState.InitialState
import com.americanexpress.android.testgenerator.screen.EditProfileCommand
import com.americanexpress.android.testgenerator.screen.EditProfileEffect
import com.americanexpress.android.testgenerator.R
import com.americanexpress.android.rxworkflow.screen.Screen
import com.americanexpress.ui.extensions.bindViewById
import com.americanexpress.ui.extensions.inflate

@SuppressLint("ViewConstructor")
@Suppress("NotImplementedDeclaration", "UnusedPrivateMember", "UNUSED_PARAMETER", "UNREACHABLE_CODE") // TODO remove during implementation!!!
class EditProfileView(
    context: Context,
    private val screen: Screen<EditProfileState, EditProfileCommand, EditProfileEffect>
) : LinearLayout(context), OnBackPressed {

    init {
        inflate(R.layout.edit_profile_view)
        orientation = VERTICAL
    }

    private val toolbar: Toolbar by bindViewById(R.id.edit_profile_toolbar)

    private val ui = userInterface<EditProfileCommand>(this)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        attachView(ui, screen, ::renderState, ::handleEffect)
    }

    private fun renderState(state: EditProfileState) {
        when (state) {
            is InitialState -> {
                toolbar.title = state.title
            }
        }
    }

    private fun handleEffect(effect: EditProfileEffect) {
        TODO()
    }

    override fun onBackPressed(): Boolean {
        TODO()
        return true
    }

}
