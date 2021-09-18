package com.adammcneilly.mvicasestudy.domain.update

import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedFavoriteDeviceLineAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedLoveForAndroidAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.UpdatedPrefersDarkThemeAnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.ViewedPreferencesAnalyticsEvent
import com.adammcneilly.mvicasestudy.redux.Middleware
import com.adammcneilly.mvicasestudy.redux.Store
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a custom [Middleware] that processes any [UpdatePreferencesAction]s and tracks necessary
 * analytics to the supplied [analyticsTracker].
 */
class UpdatePreferencesAnalyticsMiddleware(
    private val analyticsTracker: AnalyticsTracker,
) : Middleware<DeveloperPreferencesViewState, UpdatePreferencesAction> {

    override suspend fun process(
        action: UpdatePreferencesAction,
        currentState: DeveloperPreferencesViewState,
        store: Store<DeveloperPreferencesViewState, UpdatePreferencesAction>
    ) {
        when (action) {
            is UpdatePreferencesAction.PreferencesLoaded -> {
                analyticsTracker.trackEvent(ViewedPreferencesAnalyticsEvent)
            }
            is UpdatePreferencesAction.UpdatePrefersDarkTheme -> {
                val event = UpdatedPrefersDarkThemeAnalyticsEvent(action.prefersDarkTheme)
                analyticsTracker.trackEvent(event)
            }
            is UpdatePreferencesAction.UpdateFavoriteDeviceLine -> {
                val event = UpdatedFavoriteDeviceLineAnalyticsEvent(action.favoriteDeviceLine)
                analyticsTracker.trackEvent(event)
            }
            is UpdatePreferencesAction.UpdateLoveForAndroid -> {
                val event = UpdatedLoveForAndroidAnalyticsEvent(action.loveForAndroid)
                analyticsTracker.trackEvent(event)
            }
        }
    }
}
