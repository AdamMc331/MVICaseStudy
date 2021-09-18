package com.adammcneilly.mvicasestudy.redux

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This is a test implementation of a [Store] that keeps track of all historical [Action]s that
 * are dispatches so we can validate what was dispatched from a ViewModel.
 */
class FakeStore<S : State, A : Action>(
    private val initialState: S,
) : Store<S, A> {
    private val allActions: MutableList<A> = mutableListOf()

    override val state: StateFlow<S>
        get() = MutableStateFlow(initialState)

    override suspend fun dispatch(action: A) {
        allActions.add(action)
    }

    fun assertActionDispatched(action: Action) {
        assertThat(allActions).contains(action)
    }
}
