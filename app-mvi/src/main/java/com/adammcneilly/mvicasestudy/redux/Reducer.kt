package com.adammcneilly.mvicasestudy.redux

/**
 * A [Reducer] is responsible for taking a [State], and an [Action],
 * and emitting a new state via the [reduce] method.
 */
interface Reducer<S : State, A : Action> {

    /**
     * Given a [currentState] and some [action] that the user took, produce a new [State].
     *
     * This will give us clear and predictable state management, that ensures each state is associated
     * with some specific user intent or action.
     */
    fun reduce(currentState: S, action: A): S
}
