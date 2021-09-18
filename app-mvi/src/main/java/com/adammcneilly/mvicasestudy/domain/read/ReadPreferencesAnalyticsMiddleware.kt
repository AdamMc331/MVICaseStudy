package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.ViewedPreferencesAnalyticsEvent
import com.adammcneilly.mvicasestudy.redux.Middleware
import com.adammcneilly.mvicasestudy.redux.Store
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a custom [Middleware] that processes any [ReadPreferencesAction]s and tracks necessary
 * analytics to the supplied [analyticsTracker].
 */
class ReadPreferencesAnalyticsMiddleware(
    private val analyticsTracker: AnalyticsTracker,
) : Middleware<DeveloperPreferencesViewState, ReadPreferencesAction> {

    override suspend fun process(
        action: ReadPreferencesAction,
        currentState: DeveloperPreferencesViewState,
        store: Store<DeveloperPreferencesViewState, ReadPreferencesAction>
    ) {
        when (action) {
            is ReadPreferencesAction.PreferencesLoaded -> {
                analyticsTracker.trackEvent(ViewedPreferencesAnalyticsEvent)
            }
        }
    }
}
