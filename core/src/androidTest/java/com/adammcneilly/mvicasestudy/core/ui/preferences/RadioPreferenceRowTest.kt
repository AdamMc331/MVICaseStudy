package com.adammcneilly.mvicasestudy.core.ui.preferences

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.adammcneilly.mvicasestudy.core.models.RadioPreference
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class RadioPreferenceRowTest {
    private val defaultOptions = listOf("One", "Two", "Three")

    private val defaultPreference = RadioPreference(
        preferenceName = "Radio Preference",
        options = defaultOptions,
        selectedOption = null,
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayPreference() {
        val preference = defaultPreference.copy(
            selectedOption = defaultPreference.options.first(),
        )

        composeTestRule.setContent {
            RadioPreferenceRow(
                radioPreference = preference,
                optionClicked = {},
            )
        }

        composeTestRule.onNodeWithText(preference.preferenceName).assertExists()

        preference.options.forEach { option ->
            val textNode = composeTestRule.onNodeWithText(option)
            textNode.assertExists()

            val shouldBeChecked = (option == preference.selectedOption)
            val optionMatcher = SemanticsMatcher.expectValue(RadioOptionKey, option)
            val buttonNode = composeTestRule.onNode(optionMatcher)
            if (shouldBeChecked) {
                buttonNode.assertIsSelected()
            } else {
                buttonNode.assertIsNotSelected()
            }
        }
    }

    @Test
    fun clickOption() {
        val preference = defaultPreference
        var selectedOption: String? = null

        composeTestRule.setContent {
            RadioPreferenceRow(
                radioPreference = preference,
                optionClicked = { option ->
                    selectedOption = option
                }
            )
        }

        val optionToClick = preference.options.first()
        val optionMatcher = SemanticsMatcher.expectValue(RadioOptionKey, optionToClick)
        composeTestRule.onNode(optionMatcher).performClick()

        assertThat(selectedOption).isEqualTo(optionToClick)
    }
}
