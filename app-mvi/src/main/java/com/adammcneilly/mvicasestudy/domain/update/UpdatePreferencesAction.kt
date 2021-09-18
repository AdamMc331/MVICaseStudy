package com.adammcneilly.mvicasestudy.domain.update

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.redux.Action

/**
 * This sealed class
 */
sealed class UpdatePreferencesAction : Action {
    /**
     * This [Action] is triggered any time we want to begin fetching the user's preferences.
     */
    object FetchPreferences : UpdatePreferencesAction()

    /**
     * This [Action] is triggered once we've began fetching the user's preferences.
     */
    object FetchingPreferences : UpdatePreferencesAction()

    /**
     * This [Action] is triggered once we've completed fetching the user's preferences, or any time
     * an update has been made to the preferences.
     */
    data class PreferencesLoaded(val preferences: DeveloperPreferences) : UpdatePreferencesAction()

    /**
     * This [Action] is triggered when we want to update the user's prefers dark theme preference.
     */
    data class UpdatePrefersDarkTheme(val prefersDarkTheme: Boolean) : UpdatePreferencesAction()

    /**
     * This [Action] is triggered when we want to update the user's favorite device line preference.
     */
    data class UpdateFavoriteDeviceLine(val favoriteDeviceLine: String) : UpdatePreferencesAction()

    /**
     * This [Action] is triggered when we want to update the user's love for android preference.
     */
    data class UpdateLoveForAndroid(val loveForAndroid: Int) : UpdatePreferencesAction()
}
