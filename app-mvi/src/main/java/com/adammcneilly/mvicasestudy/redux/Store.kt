package com.adammcneilly.mvicasestudy.redux

import kotlinx.coroutines.flow.StateFlow

/**
 * A [Store] is used to manage [State] and dispatch [Action]s that can update the state.
 */
interface Store<S : State, A : Action> {

    val state: StateFlow<S>

    suspend fun dispatch(action: A)
}
