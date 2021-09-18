package com.adammcneilly.mvicasestudy.ui.preferences.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvicasestudy.domain.update.UpdatePreferencesAction
import com.adammcneilly.mvicasestudy.domain.update.UpdatePreferencesStore
import com.adammcneilly.mvicasestudy.redux.Action
import com.adammcneilly.mvicasestudy.redux.Store
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * In an MVI architecture, this class is the connection between the View and the Model.
 *
 * This ViewModel is specific to the update preferences screen, so it is responsible for fetching
 * preferences, and dispatching actions to update any individual preferences.
 *
 * It exposes all information to the view via [viewState], however all of the state management
 * happens inside our [store]. All the ViewModel should be responsible for is calling [Store.dispatch]
 * with the appropriate [Action].
 *
 * @property[store] This is a constructor parameter so the caller can pass any store, but we default
 * to the [UpdatePreferencesStore] for our contrived example.
 */
class UpdateDeveloperPreferencesViewModel(
    private val store: Store<DeveloperPreferencesViewState, UpdatePreferencesAction> = UpdatePreferencesStore(),
) : ViewModel() {

    val viewState: StateFlow<DeveloperPreferencesViewState> = store.state

    init {
        fetchPreferences()
    }

    private fun fetchPreferences() {
        viewModelScope.launch {
            store.dispatch(UpdatePreferencesAction.FetchPreferences)
        }
    }

    fun prefersDarkThemeChanged(prefersDarkTheme: Boolean) {
        viewModelScope.launch {
            store.dispatch(UpdatePreferencesAction.UpdatePrefersDarkTheme(prefersDarkTheme))
        }
    }

    fun favoriteDeviceLineChanged(favoriteDeviceLine: String) {
        viewModelScope.launch {
            store.dispatch(UpdatePreferencesAction.UpdateFavoriteDeviceLine(favoriteDeviceLine))
        }
    }

    fun loveForAndroidChanged(loveForAndroid: Int) {
        viewModelScope.launch {
            store.dispatch(UpdatePreferencesAction.UpdateLoveForAndroid(loveForAndroid))
        }
    }
}
