package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.core.data.PreferenceRepository
import com.adammcneilly.mvicasestudy.redux.Middleware
import com.adammcneilly.mvicasestudy.redux.Store
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a custom [Middleware] that processes any [ReadPreferencesAction]s and triggers a
 * corresponding data request to our [preferencesRepository] if necessary.
 */
class ReadPreferencesDataMiddleware(
    private val preferencesRepository: PreferenceRepository,
) : Middleware<DeveloperPreferencesViewState, ReadPreferencesAction> {
    override suspend fun process(
        action: ReadPreferencesAction,
        currentState: DeveloperPreferencesViewState,
        store: Store<DeveloperPreferencesViewState, ReadPreferencesAction>
    ) {
        when (action) {
            is ReadPreferencesAction.FetchPreferences -> {
                fetchPreferences(store)
            }
        }
    }

    /**
     * Fetches the user's preferences, and emits relevant actions to the [store] to update the state.
     */
    private suspend fun fetchPreferences(store: Store<DeveloperPreferencesViewState, ReadPreferencesAction>) {
        store.dispatch(ReadPreferencesAction.FetchingPreferences)

        val preferences = preferencesRepository.fetchPreferences()

        store.dispatch(ReadPreferencesAction.PreferencesLoaded(preferences))
    }
}
