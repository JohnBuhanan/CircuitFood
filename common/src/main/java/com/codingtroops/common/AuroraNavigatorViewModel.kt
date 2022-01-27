package com.codingtroops.common

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuroraNavigatorViewModel @Inject constructor(
    private val auroraNavigator: AuroraNavigator,
) : ViewModel(), AuroraNavigator by auroraNavigator
