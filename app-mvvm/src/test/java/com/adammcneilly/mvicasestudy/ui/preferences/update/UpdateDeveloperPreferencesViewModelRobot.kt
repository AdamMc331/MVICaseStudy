package com.adammcneilly.mvicasestudy.ui.preferences.update

import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsEvent
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.data.FakeAnalyticsTracker
import com.adammcneilly.mvicasestudy.data.FakePreferenceRepository
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import com.google.common.truth.Truth.assertThat

/**
 * A test robot serves as an additional abstraction layer between our unit tests and the system
 * under test - in this case, [UpdateDeveloperPreferencesViewModel]. We use this layer so that if we change
 * something about how our ViewModel behaves, we only need to change it here, and not in the test
 * class.
 */
class UpdateDeveloperPreferencesViewModelRobot {
    private val fakePreferenceRepository = FakePreferenceRepository()
    private val fakeAnalyticsTracker = FakeAnalyticsTracker()
    private lateinit var viewModel: UpdateDeveloperPreferencesViewModel

    fun buildViewModel() = apply {
        viewModel = UpdateDeveloperPreferencesViewModel(
            preferenceRepository = fakePreferenceRepository,
            analyticsTracker = fakeAnalyticsTracker,
        )
    }

    fun changePrefersDarkTheme(prefersDarkTheme: Boolean) = apply {
        viewModel.prefersDarkThemeChanged(prefersDarkTheme)
    }

    fun changeFavoriteDeviceLine(favoriteDeviceLine: String) = apply {
        viewModel.favoriteDeviceLineChanged(favoriteDeviceLine)
    }

    fun changeLoveForAndroid(loveForAndroid: Int) = apply {
        viewModel.loveForAndroidChanged(loveForAndroid)
    }

    fun assertViewState(expectedViewState: DeveloperPreferencesViewState) = apply {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }

    fun emitFetchPreferences(preferences: DeveloperPreferences) = apply {
        fakePreferenceRepository.emitFetchPreferences(preferences)
    }

    fun emitUpdatePreferences(preferences: DeveloperPreferences) = apply {
        fakePreferenceRepository.emitUpdatedPreferences(preferences)
    }

    fun assertEventTracked(expectedEvent: AnalyticsEvent) = apply {
        fakeAnalyticsTracker.trackEvent(expectedEvent)
    }
}
