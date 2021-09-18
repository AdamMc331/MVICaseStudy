package com.adammcneilly.mvicasestudy.core.ui.preferences

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.adammcneilly.mvicasestudy.core.models.SwitchPreference
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class SwitchPreferenceRowTest {
    private val defaultPreference = SwitchPreference(
        preferenceName = "Switch Preference",
        isChecked = false,
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayPreference() {
        val preference = defaultPreference.copy(
            isChecked = false,
        )

        composeTestRule.setContent {
            SwitchPreferenceRow(
                switchPreference = preference,
                onCheckedChanged = {},
            )
        }

        composeTestRule.onNodeWithText(preference.preferenceName).assertExists()

        composeTestRule.onNode(hasClickAction()).assertIsOff()
    }

    @Test
    fun toggleSwitch() {
        val preference = defaultPreference
        var hasClickedSwitch = false

        composeTestRule.setContent {
            SwitchPreferenceRow(
                switchPreference = preference,
                onCheckedChanged = {
                    hasClickedSwitch = true
                },
            )
        }

        composeTestRule.onNodeWithText(preference.preferenceName).assertExists()

        composeTestRule.onNode(hasClickAction()).performClick()

        assertThat(hasClickedSwitch).isTrue()
    }
}
