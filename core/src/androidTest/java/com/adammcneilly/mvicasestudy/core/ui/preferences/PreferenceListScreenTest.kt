package com.adammcneilly.mvicasestudy.core.ui.preferences

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import org.junit.Rule
import org.junit.Test

class PreferenceListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showLoader() {
        val preferences = DeveloperPreferences()
        val showLoading = true

        composeTestRule.setContent {
            DeveloperPreferencesListScreen(
                preferences = preferences,
                showLoading = showLoading,
                onPrefersDarkThemeChanged = {},
                onFavoriteDeviceLineChanged = {},
                onLoveForAndroidChanged = {},
            )
        }

        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertExists()
    }

    @Test
    fun hideLoader() {
        val preferences = DeveloperPreferences()
        val showLoading = false

        composeTestRule.setContent {
            DeveloperPreferencesListScreen(
                preferences = preferences,
                showLoading = showLoading,
                onPrefersDarkThemeChanged = {},
                onFavoriteDeviceLineChanged = {},
                onLoveForAndroidChanged = {},
            )
        }

        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertDoesNotExist()
    }
}
