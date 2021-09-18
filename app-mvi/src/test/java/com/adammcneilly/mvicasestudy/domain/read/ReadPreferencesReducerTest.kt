package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Run a series of unit tests to ensure [ReadPreferencesReducer] behaves as expected.
 */
class ReadPreferencesReducerTest {
    private val defaultViewState = DeveloperPreferencesViewState()
    private val reducer = ReadPreferencesReducer()

    @Test
    fun processFetchingPreferences() {
        val newState = reducer.reduce(defaultViewState, ReadPreferencesAction.FetchingPreferences)
        assertThat(newState.showLoading).isTrue()
    }

    @Test
    fun processPreferencesLoaded() {
        val preferences = DeveloperPreferences()

        val newState = reducer.reduce(
            defaultViewState,
            ReadPreferencesAction.PreferencesLoaded(preferences)
        )

        assertThat(newState.showLoading).isFalse()
        assertThat(newState.preferences).isEqualTo(preferences)
    }
}
