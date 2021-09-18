package com.adammcneilly.mvicasestudy.ui.preferences.read

import com.adammcneilly.mvicasestudy.CoroutineTestRule
import com.adammcneilly.mvicasestudy.domain.read.ReadPreferencesAction
import org.junit.Rule
import org.junit.Test

/**
 * This runs a series of unit tests to ensure the [ReadDeveloperPreferencesViewModel] behaves as expected.
 */
class ReadDeveloperPreferencesViewModelTest {
    private val testRobot = ReadDeveloperPreferencesViewModelRobot()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Test
    fun fetchPreferencesWhenCreated() {
        testRobot
            .buildViewModel()
            .assertActionDispatched(ReadPreferencesAction.FetchPreferences)
    }
}
