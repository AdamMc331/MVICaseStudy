package com.adammcneilly.mvicasestudy.ui.preferences

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences

/**
 * This configures the state of the UI for a screen showing the user's [preferences].
 *
 * @property[showLoading] True if we should show a progress bar, false otherwise.
 */
data class DeveloperPreferencesViewState(
    val preferences: DeveloperPreferences? = null,
    val showLoading: Boolean = false,
)
