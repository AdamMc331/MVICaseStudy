package com.adammcneilly.mvicasestudy.data

import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsEvent
import com.adammcneilly.mvicasestudy.core.data.analytics.AnalyticsTracker
import com.google.common.truth.Truth.assertThat

/**
 * A custom implementation of [AnalyticsTracker] for testing sake that tracks
 * all historical events and allows the caller to verify that an event
 * was tracked through [assertEventTracked].
 */
class FakeAnalyticsTracker : AnalyticsTracker {
    private val allEvents: MutableList<AnalyticsEvent> = mutableListOf()

    override fun trackEvent(event: AnalyticsEvent) {
        allEvents.add(event)
    }

    fun assertEventTracked(expectedEvent: AnalyticsEvent) {
        assertThat(allEvents).contains(expectedEvent)
    }
}
