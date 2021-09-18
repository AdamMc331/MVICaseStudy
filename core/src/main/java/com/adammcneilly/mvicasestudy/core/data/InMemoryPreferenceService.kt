package com.adammcneilly.mvicasestudy.core.data

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import kotlinx.coroutines.delay

/**
 * This is a concrete implementation of a [PreferenceRepository] that keeps track of all the user's
 * preferences in a local variable, updating it if necessary.
 */
class InMemoryPreferenceService(
    initialPreferences: DeveloperPreferences = DeveloperPreferences(),
) : PreferenceRepository {

    private var currentPreferences: DeveloperPreferences = initialPreferences

    /**
     * This delay is artificial and just to help demonstrate loading in the sample application.
     */
    override suspend fun fetchPreferences(): DeveloperPreferences {
        delay(2500)

        return currentPreferences
    }

    override suspend fun updatePrefersDarkTheme(prefersDarkTheme: Boolean): DeveloperPreferences {
        currentPreferences = currentPreferences.copy(
            prefersDarkTheme = prefersDarkTheme,
        )

        return currentPreferences
    }

    override suspend fun updateFavoriteDeviceLine(favoriteDeviceLine: String): DeveloperPreferences {
        currentPreferences = currentPreferences.copy(
            favoriteDeviceLine = favoriteDeviceLine,
        )

        return currentPreferences
    }

    override suspend fun updateLoveForAndroid(loveForAndroid: Int): DeveloperPreferences {
        currentPreferences = currentPreferences.copy(
            loveForAndroid = loveForAndroid,
        )

        return currentPreferences
    }
}
