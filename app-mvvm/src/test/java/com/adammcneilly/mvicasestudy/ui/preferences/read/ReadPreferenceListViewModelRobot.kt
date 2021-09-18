package com.adammcneilly.mvicasestudy.ui.preferences.read

import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsEvent
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.data.FakeAnalyticsTracker
import com.adammcneilly.mvicasestudy.data.FakePreferenceRepository
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import com.google.common.truth.Truth.assertThat

/**
 * A test robot serves as an additional abstraction layer between our unit tests and the system
 * under test - in this case, [ReadDeveloperPreferencesViewModel]. We use this layer so that if we change
 * something about how our ViewModel behaves, we only need to change it here, and not in the test
 * class.
 */
class ReadPreferenceListViewModelRobot {
    private val fakePreferenceRepository = FakePreferenceRepository()
    private val fakeAnalyticsTracker = FakeAnalyticsTracker()
    private lateinit var viewModel: ReadDeveloperPreferencesViewModel

    fun buildViewModel() = apply {
        viewModel = ReadDeveloperPreferencesViewModel(
            preferenceRepository = fakePreferenceRepository,
            analyticsTracker = fakeAnalyticsTracker,
        )
    }

    fun assertViewState(expectedViewState: DeveloperPreferencesViewState) = apply {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }

    fun emitFetchPreferences(preferences: DeveloperPreferences) = apply {
        fakePreferenceRepository.emitFetchPreferences(preferences)
    }

    fun assertEventTracked(expectedEvent: AnalyticsEvent) = apply {
        fakeAnalyticsTracker.trackEvent(expectedEvent)
    }
}
