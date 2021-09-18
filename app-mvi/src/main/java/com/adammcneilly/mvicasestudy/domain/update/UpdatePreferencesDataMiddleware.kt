package com.adammcneilly.mvicasestudy.domain.update

import com.adammcneilly.mvicasestudy.core.data.PreferenceRepository
import com.adammcneilly.mvicasestudy.redux.Middleware
import com.adammcneilly.mvicasestudy.redux.Store
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a custom [Middleware] that processes any [UpdatePreferencesAction]s and triggers a
 * corresponding data request to our [preferencesRepository] if necessary.
 */
class UpdatePreferencesDataMiddleware(
    private val preferencesRepository: PreferenceRepository,
) : Middleware<DeveloperPreferencesViewState, UpdatePreferencesAction> {
    override suspend fun process(
        action: UpdatePreferencesAction,
        currentState: DeveloperPreferencesViewState,
        store: Store<DeveloperPreferencesViewState, UpdatePreferencesAction>
    ) {
        when (action) {
            is UpdatePreferencesAction.FetchPreferences -> {
                fetchPreferences(store)
            }
            is UpdatePreferencesAction.UpdatePrefersDarkTheme -> {
                val newPreferences = preferencesRepository.updatePrefersDarkTheme(action.prefersDarkTheme)

                store.dispatch(UpdatePreferencesAction.PreferencesLoaded(newPreferences))
            }
            is UpdatePreferencesAction.UpdateFavoriteDeviceLine -> {
                val newPreferences = preferencesRepository.updateFavoriteDeviceLine(action.favoriteDeviceLine)

                store.dispatch(UpdatePreferencesAction.PreferencesLoaded(newPreferences))
            }
            is UpdatePreferencesAction.UpdateLoveForAndroid -> {
                val newPreferences = preferencesRepository.updateLoveForAndroid(action.loveForAndroid)

                store.dispatch(UpdatePreferencesAction.PreferencesLoaded(newPreferences))
            }
        }
    }

    private suspend fun fetchPreferences(store: Store<DeveloperPreferencesViewState, UpdatePreferencesAction>) {
        store.dispatch(UpdatePreferencesAction.FetchingPreferences)

        val preferences = preferencesRepository.fetchPreferences()

        store.dispatch(UpdatePreferencesAction.PreferencesLoaded(preferences))
    }
}
