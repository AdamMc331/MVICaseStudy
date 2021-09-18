package com.adammcneilly.mvicasestudy.core.data.analytics

/**
 * An [AnalyticsEvent] is any event triggered throughout the application to track user behavior,
 * such as viewing a screen or toggling preferences.
 *
 * @property[eventName] A user friendly string describing the event.
 * @property[properties] A key-value-pair map of detailed information relative to the event.
 */
interface AnalyticsEvent {
    val eventName: String
    val properties: Map<String, Any>
}
