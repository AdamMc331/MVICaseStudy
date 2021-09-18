package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.redux.Action

/**
 * This is a sealed class of all possible [Action]s that can occur on the read preferences screen.
 */
sealed class ReadPreferencesAction : Action {
    /**
     * This [Action] is triggered any time we want to begin fetching the user's preferences.
     */
    object FetchPreferences : ReadPreferencesAction()

    /**
     * This [Action] is triggered once we've began fetching the user's preferences.
     */
    object FetchingPreferences : ReadPreferencesAction()

    /**
     * This [Action] is triggered once we've completed fetching the user's preferences.
     */
    data class PreferencesLoaded(val preferences: DeveloperPreferences) : ReadPreferencesAction()
}
