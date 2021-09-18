package com.adammcneilly.mvicasestudy.core.data.analytics

/**
 * This event is tracked any time the user opens a preferences screen - through reading or updating.
 */
object ViewedPreferencesAnalyticsEvent : AnalyticsEvent {
    override val eventName: String
        get() = "View Preferences"

    override val properties: Map<String, Any>
        get() = emptyMap()
}

/**
 * This event is tracked any time the user updates their prefers dark theme preference.
 */
data class UpdatedPrefersDarkThemeAnalyticsEvent(
    val prefersDarkTheme: Boolean,
) : AnalyticsEvent {
    override val eventName: String
        get() = "Update Prefers Dark Theme"

    override val properties: Map<String, Any>
        get() = mapOf(
            "prefers_dark_theme" to prefersDarkTheme,
        )
}

/**
 * This event is tracked any time the user updates their favorite device line preference.
 */
data class UpdatedFavoriteDeviceLineAnalyticsEvent(
    val favoriteDeviceLine: String,
) : AnalyticsEvent {
    override val eventName: String
        get() = "Update Favorite Device Line"

    override val properties: Map<String, Any>
        get() = mapOf(
            "favorite_device_line" to favoriteDeviceLine,
        )
}

/**
 * This event is tracked any time the user updates their love for Android preference.
 */
data class UpdatedLoveForAndroidAnalyticsEvent(
    val loveForAndroid: Int,
) : AnalyticsEvent {
    override val eventName: String
        get() = "Update Love For Android"

    override val properties: Map<String, Any>
        get() = mapOf(
            "love_for_android" to loveForAndroid,
        )
}
