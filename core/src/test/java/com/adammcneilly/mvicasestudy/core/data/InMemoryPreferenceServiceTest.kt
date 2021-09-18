package com.adammcneilly.mvicasestudy.core.data

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * This runs a number of unit tests to verify the [InMemoryPreferenceService] works as expected.
 */
class InMemoryPreferenceServiceTest {
    private val initialPreferences = DeveloperPreferences()

    @Test
    fun fetchPreferences() = runBlockingTest {
        val service = InMemoryPreferenceService(initialPreferences)

        assertThat(service.fetchPreferences()).isEqualTo(initialPreferences)
    }

    @Test
    fun updatePrefersDarkTheme() = runBlockingTest {
        val prefersDarkTheme = true
        val service = InMemoryPreferenceService(initialPreferences)

        val updatedPreferences = service.updatePrefersDarkTheme(prefersDarkTheme)
        val expectedPreferences = initialPreferences.copy(
            prefersDarkTheme = prefersDarkTheme,
        )

        assertThat(updatedPreferences).isEqualTo(expectedPreferences)
    }

    @Test
    fun updateFavoriteDeviceLine() = runBlockingTest {
        val favoriteDeviceLine = "Pixel"
        val service = InMemoryPreferenceService(initialPreferences)

        val updatedPreferences = service.updateFavoriteDeviceLine(favoriteDeviceLine)
        val expectedPreferences = initialPreferences.copy(
            favoriteDeviceLine = favoriteDeviceLine,
        )

        assertThat(updatedPreferences).isEqualTo(expectedPreferences)
    }

    @Test
    fun updateLoveForAndroid() = runBlockingTest {
        val loveForAndroid = 100
        val service = InMemoryPreferenceService(initialPreferences)

        val updatedPreferences = service.updateLoveForAndroid(loveForAndroid)
        val expectedPreferences = initialPreferences.copy(
            loveForAndroid = loveForAndroid,
        )

        assertThat(updatedPreferences).isEqualTo(expectedPreferences)
    }
}
