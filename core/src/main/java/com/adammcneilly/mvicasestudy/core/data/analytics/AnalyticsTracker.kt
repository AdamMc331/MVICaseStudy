package com.adammcneilly.mvicasestudy.core.data.analytics

/**
 * An [AnalyticsTracker] is any service that has the ability to process [AnalyticsEvent]s and send
 * them to a service to be recorded.
 */
interface AnalyticsTracker {

    /**
     * Given an [event], track that so we can understand user behavior.
     */
    fun trackEvent(event: AnalyticsEvent)
}
