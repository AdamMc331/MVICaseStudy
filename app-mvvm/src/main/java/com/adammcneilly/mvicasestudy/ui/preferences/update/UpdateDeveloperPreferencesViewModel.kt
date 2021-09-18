package com.adammcneilly.mvicasestudy.ui.preferences.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvicasestudy.core.data.InMemoryPreferenceService
import com.adammcneilly.mvicasestudy.core.data.PreferenceRepository
import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.DemoAnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedFavoriteDeviceLineAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedLoveForAndroidAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedPrefersDarkThemeAnalyticsEvent
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
 * This ViewModel is specific to the update preferences screen, so it is responsible for fetching
 * preferences and updating any individual preferences.
 *
 * @property[preferenceRepository] Used to make any data layer requests for the user's preferences.
 * @property[analyticsTracker] Tracking any domain specific analytics events triggered on this screen.
 */
class UpdateDeveloperPreferencesViewModel(
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

    fun prefersDarkThemeChanged(prefersDarkTheme: Boolean) {
        viewModelScope.launch {
            val newPreferences = preferenceRepository.updatePrefersDarkTheme(prefersDarkTheme)

            _viewState.value = _viewState.value.copy(
                preferences = newPreferences
            )

            val event = UpdatedPrefersDarkThemeAnalyticsEvent(
                prefersDarkTheme = prefersDarkTheme,
            )
            analyticsTracker.trackEvent(event)
        }
    }

    fun favoriteDeviceLineChanged(favoriteDeviceLine: String) {
        viewModelScope.launch {
            val newPreferences = preferenceRepository.updateFavoriteDeviceLine(favoriteDeviceLine)

            _viewState.value = _viewState.value.copy(
                preferences = newPreferences
            )

            val event = UpdatedFavoriteDeviceLineAnalyticsEvent(
                favoriteDeviceLine = favoriteDeviceLine,
            )
            analyticsTracker.trackEvent(event)
        }
    }

    fun loveForAndroidChanged(loveForAndroid: Int) {
        viewModelScope.launch {
            val newPreferences = preferenceRepository.updateLoveForAndroid(loveForAndroid)

            _viewState.value = _viewState.value.copy(
                preferences = newPreferences
            )

            val event = UpdatedLoveForAndroidAnalyticsEvent(
                loveForAndroid = loveForAndroid,
            )
            analyticsTracker.trackEvent(event)
        }
    }
}
