package com.americanexpress.android.testgenerator.ui

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.americanexpress.android.rxworkflow.view.userInterface
import com.americanexpress.android.rxworkflow.view.attachView
import com.americanexpress.android.rxworkflow.view.OnBackPressed
import com.americanexpress.android.testgenerator.screen.HomeState
import com.americanexpress.android.testgenerator.screen.HomeState.InitialState
import com.americanexpress.android.testgenerator.screen.HomeCommand
import com.americanexpress.android.testgenerator.screen.HomeEffect
import com.americanexpress.android.testgenerator.R
import com.americanexpress.android.rxworkflow.screen.Screen
import com.americanexpress.ui.extensions.bindViewById
import com.americanexpress.ui.extensions.inflate

@SuppressLint("ViewConstructor")
@Suppress("NotImplementedDeclaration", "UnusedPrivateMember", "UNUSED_PARAMETER", "UNREACHABLE_CODE") // TODO remove during implementation!!!
class HomeView(
    context: Context,
    private val screen: Screen<HomeState, HomeCommand, HomeEffect>
) : LinearLayout(context), OnBackPressed {

    init {
        inflate(R.layout.home_view)
        orientation = VERTICAL
    }

    private val toolbar: Toolbar by bindViewById(R.id.home_toolbar)

    private val ui = userInterface<HomeCommand>(this)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        attachView(ui, screen, ::renderState, ::handleEffect)
    }

    private fun renderState(state: HomeState) {
        when (state) {
            is InitialState -> {
                toolbar.title = state.title
            }
        }
    }

    private fun handleEffect(effect: HomeEffect) {
        TODO()
    }

    override fun onBackPressed(): Boolean {
        TODO()
        return true
    }

}
