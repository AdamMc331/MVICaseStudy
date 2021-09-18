package com.adammcneilly.mvicasestudy.domain.update

import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedFavoriteDeviceLineAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedLoveForAndroidAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedPrefersDarkThemeAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.ViewedPreferencesAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.data.FakeAnalyticsTracker
import com.adammcneilly.mvicasestudy.redux.FakeStore
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * Run a series of unit tests to ensure [UpdatePreferencesAnalyticsMiddleware] behaves as expected.
 */
class UpdatePreferencesAnalyticsMiddlewareTest {
    private val fakeAnalyticsTracker = FakeAnalyticsTracker()
    private val defaultViewState = DeveloperPreferencesViewState()
    private val fakeStore =
        FakeStore<DeveloperPreferencesViewState, UpdatePreferencesAction>(defaultViewState)
    private val middleware = UpdatePreferencesAnalyticsMiddleware(fakeAnalyticsTracker)

    @Test
    fun trackViewedPreferencesOnceLoaded() = runBlockingTest {
        middleware.process(
            UpdatePreferencesAction.PreferencesLoaded(DeveloperPreferences()),
            defaultViewState,
            fakeStore,
        )

        fakeAnalyticsTracker.assertEventTracked(ViewedPreferencesAnalyticsEvent)
    }

    @Test
    fun trackUpdatePrefersDarkTheme() = runBlockingTest {
        val prefersDarkTheme = true

        middleware.process(
            UpdatePreferencesAction.UpdatePrefersDarkTheme(prefersDarkTheme),
            defaultViewState,
            fakeStore,
        )

        fakeAnalyticsTracker.assertEventTracked(
            UpdatedPrefersDarkThemeAnalyticsEvent(prefersDarkTheme)
        )
    }

    @Test
    fun trackUpdateFavoriteDeviceLine() = runBlockingTest {
        val favoriteDeviceLine = "Pixel"

        middleware.process(
            UpdatePreferencesAction.UpdateFavoriteDeviceLine(favoriteDeviceLine),
            defaultViewState,
            fakeStore,
        )

        fakeAnalyticsTracker.assertEventTracked(
            UpdatedFavoriteDeviceLineAnalyticsEvent(favoriteDeviceLine)
        )
    }

    @Test
    fun trackUpdatedLoveForAndroid() = runBlockingTest {
        val loveForAndroid = 100

        middleware.process(
            UpdatePreferencesAction.UpdateLoveForAndroid(loveForAndroid),
            defaultViewState,
            fakeStore,
        )

        fakeAnalyticsTracker.assertEventTracked(
            UpdatedLoveForAndroidAnalyticsEvent(loveForAndroid)
        )
    }
}
