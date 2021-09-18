package com.adammcneilly.mvicasestudy.core.ui.preferences

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import org.junit.Rule
import org.junit.Test

class PreferenceListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderDeveloperPreferences() {
        val preferences = DeveloperPreferences()

        composeTestRule.setContent {
            DeveloperPreferencesList(
                preferences = preferences,
                onPrefersDarkThemeChanged = {},
                onFavoriteDeviceLineChanged = {},
                onLoveForAndroidChanged = {},
            )
        }

        composeTestRule
            .onNode(SemanticsMatcher.expectValue(PreferenceSemanticsKey, "Prefers Dark Theme"))
            .assertExists()

        composeTestRule
            .onNode(SemanticsMatcher.expectValue(PreferenceSemanticsKey, "Favorite Device Line"))
            .assertExists()

        composeTestRule
            .onNode(SemanticsMatcher.expectValue(PreferenceSemanticsKey, "Love For Android"))
            .assertExists()
    }
}
