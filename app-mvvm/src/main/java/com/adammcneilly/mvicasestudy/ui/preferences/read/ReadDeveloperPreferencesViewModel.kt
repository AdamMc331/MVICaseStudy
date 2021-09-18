package com.adammcneilly.mvicasestudy.ui.preferences.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvicasestudy.core.data.InMemoryPreferenceService
import com.adammcneilly.mvicasestudy.core.data.PreferenceRepository
import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.DemoAnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.ViewedPreferencesAnalyticsEvent
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * In an MVVM architecture, this class is the connection between the View and the Model.
 *
 * It exposes all information to the view via [viewState], requests information via
 * the [preferenceRepository], and will manage all of the business logic inside. It can also work
 * with other dependencies, like the [analyticsTracker].
 *
 * This ViewModel is specific to the read preference screen, so all it is responsible for is fetching
 * the user's preferences.
 *
 * @property[preferenceRepository] Used to make any data layer requests for the user's preferences.
 * @property[analyticsTracker] Tracking any domain specific analytics events triggered on this screen.
 */
class ReadDeveloperPreferencesViewModel(
    private val preferenceRepository: PreferenceRepository = InMemoryPreferenceService(),
    private val analyticsTracker: AnalyticsTracker = DemoAnalyticsTracker(),
) : ViewModel() {
    private val _viewState = MutableStateFlow(DeveloperPreferencesViewState())
    val viewState: StateFlow<DeveloperPreferencesViewState> = _viewState

    init {
        fetchPreferences()
    }

    private fun fetchPreferences() {
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(
                showLoading = true,
            )

            val preferences = preferenceRepository.fetchPreferences()

            _viewState.value = _viewState.value.copy(
                preferences = preferences,
                showLoading = false,
            )

            analyticsTracker.trackEvent(ViewedPreferencesAnalyticsEvent)
        }
    }
}
