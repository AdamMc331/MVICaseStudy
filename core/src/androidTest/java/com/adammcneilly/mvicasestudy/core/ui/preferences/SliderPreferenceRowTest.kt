package com.adammcneilly.mvicasestudy.core.ui.preferences

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performSemanticsAction
import com.adammcneilly.mvicasestudy.core.models.SliderPreference
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class SliderPreferenceRowTest {
    private val defaultPreference = SliderPreference(
        preferenceName = "Slider Preference",
        range = 1F..10F,
        selectedValue = 5F,
        steps = 10,
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayPreference() {
        val preference = defaultPreference

        composeTestRule.setContent {
            SliderPreferenceRow(
                sliderPreference = preference,
                onValueChanged = {},
            )
        }

        val rangeInfo = ProgressBarRangeInfo(
            preference.selectedValue,
            preference.range,
            preference.steps,
        )

        composeTestRule.onNodeWithText(preference.preferenceName).assertExists()
        composeTestRule.onNodeWithText(preference.selectedValue.toInt().toString()).assertExists()
        composeTestRule.onNode(hasProgressBarRangeInfo(rangeInfo)).assertExists()
    }

    @Test
    fun sliderValueChanged() {
        val preference = defaultPreference
        var newValue: Float? = null

        composeTestRule.setContent {
            SliderPreferenceRow(
                sliderPreference = preference,
                onValueChanged = { value ->
                    newValue = value
                },
            )
        }

        val rangeInfo = ProgressBarRangeInfo(
            preference.selectedValue,
            preference.range,
            preference.steps,
        )

        composeTestRule.onNodeWithText(preference.preferenceName).assertExists()
        composeTestRule.onNodeWithText(preference.selectedValue.toInt().toString()).assertExists()

        composeTestRule
            .onNode(hasProgressBarRangeInfo(rangeInfo))
            .performSemanticsAction(
                key = SemanticsActions.SetProgress,
                invocation = { invocation ->
                    invocation.invoke(5F)
                },
            )

        assertThat(newValue?.toInt()).isEqualTo(5)
    }
}
