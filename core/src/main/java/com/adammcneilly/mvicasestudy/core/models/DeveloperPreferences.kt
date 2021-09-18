package com.adammcneilly.mvicasestudy.core.models

/**
 * This class encapsulates all of the Android Dev preferences a user can have.
 *
 * @property[prefersDarkTheme] True if the user prefers dark theme, false otherwise.
 * @property[favoriteDeviceLine] The user's favorite phone collection, if any.
 * @property[loveForAndroid] The user's love for Android, on a scale of 1-10.
 */
data class DeveloperPreferences(
    val prefersDarkTheme: Boolean = false,
    val favoriteDeviceLine: String? = null,
    val loveForAndroid: Int = 10,
)
