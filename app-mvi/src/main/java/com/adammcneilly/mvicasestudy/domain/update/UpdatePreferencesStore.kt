package com.adammcneilly.mvicasestudy.domain.update

import com.adammcneilly.mvicasestudy.core.data.InMemoryPreferenceService
import com.adammcneilly.mvicasestudy.core.data.PreferenceRepository
import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.DemoAnalyticsTracker
import com.adammcneilly.mvicasestudy.redux.BaseStore
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a custom implementation of a [BaseStore] specific to the update preferences screen.
 */
class UpdatePreferencesStore(
    preferencesRepository: PreferenceRepository = InMemoryPreferenceService(),
    analyticsTracker: AnalyticsTracker = DemoAnalyticsTracker(),
) : BaseStore<DeveloperPreferencesViewState, UpdatePreferencesAction>(
    initialState = DeveloperPreferencesViewState(),
    reducer = UpdatePreferencesReducer(),
    middlewares = listOf(
        UpdatePreferencesDataMiddleware(preferencesRepository),
        UpdatePreferencesAnalyticsMiddleware(analyticsTracker),
    ),
)
