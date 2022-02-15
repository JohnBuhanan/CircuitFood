package com.americanexpress.android.${featurename}.ui

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.americanexpress.android.rxworkflow.view.userInterface
import com.americanexpress.android.rxworkflow.view.attachView
import com.americanexpress.android.rxworkflow.view.OnBackPressed
import com.americanexpress.android.${featurename}.screen.${ScreenName}State
import com.americanexpress.android.${featurename}.screen.${ScreenName}State.InitialState
import com.americanexpress.android.${featurename}.screen.${ScreenName}Command
import com.americanexpress.android.${featurename}.screen.${ScreenName}Effect
import com.americanexpress.android.${featurename}.R
import com.americanexpress.android.rxworkflow.screen.Screen
import com.americanexpress.ui.extensions.bindViewById
import com.americanexpress.ui.extensions.inflate

@SuppressLint("ViewConstructor")
@Suppress("NotImplementedDeclaration", "UnusedPrivateMember", "UNUSED_PARAMETER", "UNREACHABLE_CODE") // TODO remove during implementation!!!
class ${ScreenName}View(
    context: Context,
    private val screen: Screen<${ScreenName}State, ${ScreenName}Command, ${ScreenName}Effect>
) : LinearLayout(context), OnBackPressed {

    init {
        inflate(R.layout.${screen_name}_view)
        orientation = VERTICAL
    }

    private val toolbar: Toolbar by bindViewById(R.id.${screen_name}_toolbar)

    private val ui = userInterface<${ScreenName}Command>(this)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        attachView(ui, screen, ::renderState, ::handleEffect)
    }

    private fun renderState(state: ${ScreenName}State) {
        when (state) {
            is InitialState -> {
                toolbar.title = state.title
            }
        }
    }

    private fun handleEffect(effect: ${ScreenName}Effect) {
        TODO()
    }

    override fun onBackPressed(): Boolean {
        TODO()
        return true
    }

}
