package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.core.data.InMemoryPreferenceService
import com.adammcneilly.mvicasestudy.core.data.PreferenceRepository
import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsTracker
import com.adammcneilly.mvicasestudy.core.data.analytics.DemoAnalyticsTracker
import com.adammcneilly.mvicasestudy.redux.BaseStore
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * This is a custom implementation of a [BaseStore] specific to the read preferences screen.
 */
class ReadPreferencesStore(
    preferencesRepository: PreferenceRepository = InMemoryPreferenceService(),
    analyticsTracker: AnalyticsTracker = DemoAnalyticsTracker(),
) : BaseStore<DeveloperPreferencesViewState, ReadPreferencesAction>(
    initialState = DeveloperPreferencesViewState(),
    reducer = ReadPreferencesReducer(),
    middlewares = listOf(
        ReadPreferencesDataMiddleware(preferencesRepository),
        ReadPreferencesAnalyticsMiddleware(analyticsTracker),
    ),
)
