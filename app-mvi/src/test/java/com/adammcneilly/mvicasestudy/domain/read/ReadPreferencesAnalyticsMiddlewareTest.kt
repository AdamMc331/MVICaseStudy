package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.core.data.analytics.ViewedPreferencesAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.data.FakeAnalyticsTracker
import com.adammcneilly.mvicasestudy.redux.FakeStore
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * Run a series of unit tests to ensure [ReadPreferencesAnalyticsMiddleware] behaves as expected.
 */
class ReadPreferencesAnalyticsMiddlewareTest {
    private val fakeAnalyticsTracker = FakeAnalyticsTracker()
    private val defaultViewState = DeveloperPreferencesViewState()
    private val fakeStore =
        FakeStore<DeveloperPreferencesViewState, ReadPreferencesAction>(defaultViewState)

    @Test
    fun trackViewedPreferencesOnceLoaded() = runBlockingTest {
        val middleware = ReadPreferencesAnalyticsMiddleware(fakeAnalyticsTracker)

        middleware.process(
            ReadPreferencesAction.PreferencesLoaded(DeveloperPreferences()),
            defaultViewState,
            fakeStore,
        )

        fakeAnalyticsTracker.assertEventTracked(ViewedPreferencesAnalyticsEvent)
    }
}
