package com.adammcneilly.mvicasestudy.domain.read

import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.data.FakePreferenceRepository
import com.adammcneilly.mvicasestudy.redux.FakeStore
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * Run a series of unit tests to ensure [ReadPreferencesDataMiddleware] behaves as expected.
 */
class ReadPreferencesDataMiddlewareTest {
    private val fakePreferenceRepository = FakePreferenceRepository()
    private val defaultViewState = DeveloperPreferencesViewState()
    private val fakeStore =
        FakeStore<DeveloperPreferencesViewState, ReadPreferencesAction>(defaultViewState)

    @Test
    fun processFetchPreferencesEvent() = runBlockingTest {
        val middleware = ReadPreferencesDataMiddleware(fakePreferenceRepository)

        launch {
            middleware.process(
                ReadPreferencesAction.FetchPreferences,
                defaultViewState,
                fakeStore,
            )
        }

        val fetchedPreferences = DeveloperPreferences()
        fakePreferenceRepository.emitFetchPreferences(fetchedPreferences)

        fakeStore.assertActionDispatched(ReadPreferencesAction.FetchingPreferences)
        fakeStore.assertActionDispatched(ReadPreferencesAction.PreferencesLoaded(fetchedPreferences))
    }
}
