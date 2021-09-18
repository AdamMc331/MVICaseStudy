package com.adammcneilly.mvicasestudy.ui.preferences

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.redux.State

/**
 * This configures the state of the UI for a screen showing the user's [preferences].
 */
data class DeveloperPreferencesViewState(
    val preferences: DeveloperPreferences? = null,
    val showLoading: Boolean = false,
) : State
