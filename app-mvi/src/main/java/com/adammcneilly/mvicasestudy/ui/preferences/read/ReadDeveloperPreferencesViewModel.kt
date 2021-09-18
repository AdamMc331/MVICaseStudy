package com.adammcneilly.mvicasestudy.ui.preferences.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvicasestudy.domain.read.ReadPreferencesAction
import com.adammcneilly.mvicasestudy.domain.read.ReadPreferencesStore
import com.adammcneilly.mvicasestudy.redux.Action
import com.adammcneilly.mvicasestudy.redux.Store
import com.adammcneilly.mvicasestudy.ui.preferences.DeveloperPreferencesViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * In an MVI architecture, this class is the connection between the View and the Model.
 *
 * This ViewModel is specific to the read preferences screen, so all it is responsible for is
 * fetching preferences.
 *
 * It exposes all information to the view via [viewState], however all of the state management
 * happens inside our [store]. All the ViewModel should be responsible for is calling [Store.dispatch]
 * with the appropriate [Action].
 *
 * @property[store] This is a constructor parameter so the caller can pass any store, but we default
 * to the [ReadPreferencesStore] for our contrived example.
 */
class ReadDeveloperPreferencesViewModel(
    private val store: Store<DeveloperPreferencesViewState, ReadPreferencesAction> = ReadPreferencesStore(),
) : ViewModel() {

    val viewState: StateFlow<DeveloperPreferencesViewState> = store.state

    init {
        fetchPreferences()
    }

    private fun fetchPreferences() {
        viewModelScope.launch {
            store.dispatch(ReadPreferencesAction.FetchPreferences)
        }
    }
}
