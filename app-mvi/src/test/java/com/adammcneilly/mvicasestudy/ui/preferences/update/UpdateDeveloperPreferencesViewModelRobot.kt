package com.adammcneilly.mvicasestudy.ui.preferences.update

import com.adammcneilly.mvicasestudy.domain.update.UpdatePreferencesAction
import com.adammcneilly.mvicasestudy.redux.Action
import com.adammcneilly.mvicasestudy.redux.FakeStore
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState

/**
 * A test robot serves as an additional abstraction layer between our unit tests and the system
 * under test - in this case, [UpdateDeveloperPreferencesViewModel]. We use this layer so that if we change
 * something about how our ViewModel behaves, we only need to change it here, and not in the test
 * class.
 */
class UpdateDeveloperPreferencesViewModelRobot {
    private val fakeStore = FakeStore<DeveloperPreferencesViewState, UpdatePreferencesAction>(
        initialState = DeveloperPreferencesViewState(),
    )
    private lateinit var viewModel: UpdateDeveloperPreferencesViewModel

    fun buildViewModel() = apply {
        viewModel = UpdateDeveloperPreferencesViewModel(
            store = fakeStore,
        )
    }

    fun changePrefersDarkTheme(prefersDarkTheme: Boolean) = apply {
        viewModel.prefersDarkThemeChanged(prefersDarkTheme)
    }

    fun changeFavoriteDeviceLine(favoriteDeviceLine: String) = apply {
        viewModel.favoriteDeviceLineChanged(favoriteDeviceLine)
    }

    fun changeLoveForAndroid(loveForAndroid: Int) = apply {
        viewModel.loveForAndroidChanged(loveForAndroid)
    }

    fun assertActionDispatched(expectedAction: Action) = apply {
        fakeStore.assertActionDispatched(expectedAction)
    }
}
