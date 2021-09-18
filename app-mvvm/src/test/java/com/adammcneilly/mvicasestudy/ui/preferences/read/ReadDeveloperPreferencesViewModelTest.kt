package com.adammcneilly.mvicasestudy.ui.preferences.read

import com.adammcneilly.mvicasestudy.CoroutineTestRule
import com.adammcneilly.mvicasestudy.core.data.analytics.ViewedPreferencesAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import org.junit.Rule
import org.junit.Test

/**
 * This runs a series of unit tests to ensure the [ReadDeveloperPreferencesViewModel] behaves as expected.
 */
class ReadDeveloperPreferencesViewModelTest {
    private val testRobot = ReadPreferenceListViewModelRobot()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Test
    fun fetchPreferencesWhenCreated() {
        val preferences = DeveloperPreferences()

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
}
