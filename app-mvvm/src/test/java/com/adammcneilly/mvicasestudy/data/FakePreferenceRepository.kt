package com.adammcneilly.mvicasestudy.data

import com.adammcneilly.mvicasestudy.core.data.PreferenceRepository
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * This is a concrete implementation of [PreferenceRepository] that will be used inside of unit tests.
 *
 * It exposes public functions that allow the caller to control when responses are returned from these
 * methods - this will allow us to test loading states accurately.
 */
class FakePreferenceRepository : PreferenceRepository {
    /**
     * This is a coroutine [Continuation] that will be continued any time we want to emit a response
     * from [fetchPreferences].
     */
    private lateinit var fetchContinuation: Continuation<DeveloperPreferences>

    /**
     * This is a coroutine [Continuation] that will be continued any time we want to emit a response
     * from one of the relevant update methods.
     */
    private lateinit var updateContinuation: Continuation<DeveloperPreferences>

    override suspend fun fetchPreferences(): DeveloperPreferences {
        return suspendCoroutine { continuation ->
            fetchContinuation = continuation
        }
    }

    override suspend fun updatePrefersDarkTheme(prefersDarkTheme: Boolean): DeveloperPreferences {
        return suspendCoroutine { continuation ->
            updateContinuation = continuation
        }
    }

    override suspend fun updateFavoriteDeviceLine(favoriteDeviceLine: String): DeveloperPreferences {
        return suspendCoroutine { continuation ->
            updateContinuation = continuation
        }
    }

    override suspend fun updateLoveForAndroid(loveForAndroid: Int): DeveloperPreferences {
        return suspendCoroutine { continuation ->
            updateContinuation = continuation
        }
    }

    /**
     * Emits the supplied [preferences] to the [fetchContinuation].
     */
    fun emitFetchPreferences(preferences: DeveloperPreferences) {
        fetchContinuation.resume(preferences)
    }

    /**
     * Emits the supplied [preferences] to our [updateContinuation].
     */
    fun emitUpdatedPreferences(preferences: DeveloperPreferences) {
        updateContinuation.resume(preferences)
    }
}
