package com.adammcneilly.mvicasestudy.core.models

/**
 * A [SwitchPreference] can be used for any preference represented by a boolean as on or off.
 *
 * @property[isChecked] True if the user has selected this preference, false otherwise.
 */
data class SwitchPreference(
    val preferenceName: String,
    val isChecked: Boolean,
)
