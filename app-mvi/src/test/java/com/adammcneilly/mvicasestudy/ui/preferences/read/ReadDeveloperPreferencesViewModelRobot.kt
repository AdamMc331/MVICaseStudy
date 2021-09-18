package com.adammcneilly.mvicasestudy.ui.preferences.read

import com.adammcneilly.mvicasestudy.domain.read.ReadPreferencesAction
import com.adammcneilly.mvicasestudy.redux.Action
import com.adammcneilly.mvicasestudy.redux.FakeStore
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * A test robot serves as an additional abstraction layer between our unit tests and the system
 * under test - in this case, [ReadDeveloperPreferencesViewModel]. We use this layer so that if we change
 * something about how our ViewModel behaves, we only need to change it here, and not in the test
 * class.
 */
class ReadDeveloperPreferencesViewModelRobot {
    private val fakeStore = FakeStore<DeveloperPreferencesViewState, ReadPreferencesAction>(
        initialState = DeveloperPreferencesViewState(),
    )
    private lateinit var viewModel: ReadDeveloperPreferencesViewModel

    fun buildViewModel() = apply {
        viewModel = ReadDeveloperPreferencesViewModel(
            store = fakeStore,
        )
    }

    fun assertActionDispatched(expectedAction: Action) = apply {
        fakeStore.assertActionDispatched(expectedAction)
    }
}
