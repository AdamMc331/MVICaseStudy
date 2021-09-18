package com.adammcneilly.mvicasestudy.core.models

/**
 * This data class represents a preference that will be displayed using Radio Buttons.
 *
 * @property[options] A list of strings that the user can select.
 * @property[selectedOption] The current string that the user has selected, if any.
 */
data class RadioPreference(
    val preferenceName: String,
    val options: List<String>,
    val selectedOption: String? = null,
)
