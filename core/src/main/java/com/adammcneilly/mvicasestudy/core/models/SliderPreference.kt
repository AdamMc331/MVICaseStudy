package com.adammcneilly.mvicasestudy.core.models

/**
 * A [SliderPreference] is any preference that can be portrayed using a sliding scale.
 *
 * @property[range] The floating point range that applies to this preference - for example, 0F..10F.
 * @property[selectedValue] The current value inside our [range] that the user has selected.
 * @property[steps] The discrete number of steps between the start and end value of our [range],
 * which will limit what the user can select for the [selectedValue].
 */
data class SliderPreference(
    val preferenceName: String,
    val range: ClosedFloatingPointRange<Float>,
    val selectedValue: Float,
    val steps: Int,
)
