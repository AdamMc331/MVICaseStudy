package com.adammcneilly.mvicasestudy.domain.update

import com.adammcneilly.mvicasestudy.redux.Reducer
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a concrete implementation of a [Reducer] that processes [UpdatePreferencesAction]s and
 * maps those actions to a new [DeveloperPreferencesViewState].
 */
class UpdatePreferencesReducer : Reducer<DeveloperPreferencesViewState, UpdatePreferencesAction> {

    override fun reduce(
        currentState: DeveloperPreferencesViewState,
        action: UpdatePreferencesAction
    ): DeveloperPreferencesViewState {
        return when (action) {
            is UpdatePreferencesAction.FetchingPreferences -> {
                currentState.copy(showLoading = true)
            }
            is UpdatePreferencesAction.PreferencesLoaded -> {
                currentState.copy(
                    preferences = action.preferences,
                    showLoading = false,
                )
            }
            else -> currentState
        }
    }
}
