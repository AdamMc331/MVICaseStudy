package com.adammcneilly.mvicasestudy.ui.preferences.update

import com.adammcneilly.mvicasestudy.CoroutineTestRule
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedFavoriteDeviceLineAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedLoveForAndroidAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedPrefersDarkThemeAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.ViewedPreferencesAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import org.junit.Rule
import org.junit.Test

/**
 * This runs a series of unit tests to ensure the [UpdateDeveloperPreferencesViewModel] behaves as expected.
 */
class UpdateDeveloperPreferencesViewModelTest {
    private val testRobot = UpdateDeveloperPreferencesViewModelRobot()
    private val defaultPreferences = DeveloperPreferences()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Test
    fun fetchPreferencesWhenCreated() {
        val preferences = defaultPreferences

        testRobot
            .buildViewModel()
            .assertViewState(
                expectedViewState = DeveloperPreferencesViewState(
                    preferences = null,
                    showLoading = true,
                )
            )
            .emitFetchPreferences(preferences)
            .assertViewState(
                expectedViewState = DeveloperPreferencesViewState(
                    preferences = preferences,
                    showLoading = false,
                )
            )
            .assertEventTracked(ViewedPreferencesAnalyticsEvent)
    }

    @Test
    fun updatePrefersDarkTheme() {
        val preferences = defaultPreferences

        val updatedPrefersDarkTheme = !preferences.prefersDarkTheme

        val updatedPreferences = preferences.copy(
            prefersDarkTheme = updatedPrefersDarkTheme,
        )

        testRobot
            .buildViewModel()
            .emitFetchPreferences(preferences)
            .changePrefersDarkTheme(updatedPrefersDarkTheme)
            .emitUpdatePreferences(updatedPreferences)
            .assertViewState(
                expectedViewState = DeveloperPreferencesViewState(
                    preferences = updatedPreferences,
                    showLoading = false,
                )
            )
            .assertEventTracked(
                expectedEvent = UpdatedPrefersDarkThemeAnalyticsEvent(
                    prefersDarkTheme = updatedPrefersDarkTheme,
                )
            )
    }

    @Test
    fun updateFavoriteDeviceLine() {
        val preferences = defaultPreferences

        val updatedFavoriteDeviceLine = "Test device line"

        val updatedPreferences = preferences.copy(
            favoriteDeviceLine = updatedFavoriteDeviceLine,
        )

        testRobot
            .buildViewModel()
            .emitFetchPreferences(preferences)
            .changeFavoriteDeviceLine(updatedFavoriteDeviceLine)
            .emitUpdatePreferences(updatedPreferences)
            .assertViewState(
                expectedViewState = DeveloperPreferencesViewState(
                    preferences = updatedPreferences,
                    showLoading = false,
                )
            )
            .assertEventTracked(
                expectedEvent = UpdatedFavoriteDeviceLineAnalyticsEvent(
                    favoriteDeviceLine = updatedFavoriteDeviceLine,
                )
            )
    }

    @Test
    fun updateLoveForAndroid() {
        val preferences = defaultPreferences

        val updatedLoveForAndroid = 123

        val updatedPreferences = preferences.copy(
            loveForAndroid = updatedLoveForAndroid,
        )

        testRobot
            .buildViewModel()
            .emitFetchPreferences(preferences)
            .changeLoveForAndroid(updatedLoveForAndroid)
            .emitUpdatePreferences(updatedPreferences)
            .assertViewState(
                expectedViewState = DeveloperPreferencesViewState(
                    preferences = updatedPreferences,
                    showLoading = false,
                )
            )
            .assertEventTracked(
                expectedEvent = UpdatedLoveForAndroidAnalyticsEvent(
                    loveForAndroid = updatedLoveForAndroid,
                )
            )
    }
}
