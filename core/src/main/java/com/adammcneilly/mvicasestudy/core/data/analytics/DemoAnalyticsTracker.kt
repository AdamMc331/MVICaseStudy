package com.adammcneilly.mvicasestudy.core.data.analytics

import android.util.Log

/**
 * This is a concrete implementation of [AnalyticsTracker] that will just output events to the logcat
 * so user's debugging this application can understand the flow of events.
 */
class DemoAnalyticsTracker : AnalyticsTracker {
    override fun trackEvent(event: AnalyticsEvent) {
        Log.d("DemoAnalyticsTracker", "Tracking event: $event")
    }
}
