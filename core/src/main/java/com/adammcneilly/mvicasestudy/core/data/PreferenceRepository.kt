package com.adammcneilly.mvicasestudy.core.data

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences

/**
 * This class defines our data layer for requesting user preferences, and updating them if necessary.
 */
interface PreferenceRepository {
    /**
     * Requests the [DeveloperPreferences] for the signed in user.
     */
    suspend fun fetchPreferences(): DeveloperPreferences

    /**
     * Updates the [prefersDarkTheme] value on the user's [DeveloperPreferences].
     *
     * @return The updated [DeveloperPreferences] entity.
     */
    suspend fun updatePrefersDarkTheme(prefersDarkTheme: Boolean): DeveloperPreferences

    /**
     * Updates the [favoriteDeviceLine] value on the user's [DeveloperPreferences].
     *
     * @return The updated [DeveloperPreferences] entity.
     */
    suspend fun updateFavoriteDeviceLine(favoriteDeviceLine: String): DeveloperPreferences

    /**
     * Updates the [loveForAndroid] value on the user's [DeveloperPreferences].
     *
     * @return The updated [DeveloperPreferences] entity.
     */
    suspend fun updateLoveForAndroid(loveForAndroid: Int): DeveloperPreferences
}
