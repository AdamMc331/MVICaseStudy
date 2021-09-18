package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.redux.Reducer
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a concrete implementation of a [Reducer] that processes [ReadPreferencesAction]s and
 * maps those actions to a new [DeveloperPreferencesViewState].
 */
class ReadPreferencesReducer : Reducer<DeveloperPreferencesViewState, ReadPreferencesAction> {

    override fun reduce(
        currentState: DeveloperPreferencesViewState,
        action: ReadPreferencesAction
    ): DeveloperPreferencesViewState {
        return when (action) {
            is ReadPreferencesAction.FetchingPreferences -> {
                currentState.copy(showLoading = true)
            }
            is ReadPreferencesAction.PreferencesLoaded -> {
                currentState.copy(
                    preferences = action.preferences,
                    showLoading = false,
                )
            }
            else -> currentState
        }
    }
}
