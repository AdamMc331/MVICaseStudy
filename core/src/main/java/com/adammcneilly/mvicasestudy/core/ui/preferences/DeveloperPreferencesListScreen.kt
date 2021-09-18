package com.adammcneilly.mvicasestudy.core.ui.preferences

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.core.ui.CenteredProgressBar
import com.adammcneilly.mvicasestudy.core.ui.theme.MVICaseStudyTheme

/**
 * Given a list of [preferences] and whether or not we should [showLoading], configure the UI
 * for a list of preferences.
 *
 * Any changes to preferences are exposed via the various lambdas supplied.
 */
@Composable
fun DeveloperPreferencesListScreen(
    preferences: DeveloperPreferences?,
    showLoading: Boolean,
    onPrefersDarkThemeChanged: (Boolean) -> Unit,
    onFavoriteDeviceLineChanged: (String) -> Unit,
    onLoveForAndroidChanged: (Int) -> Unit,
) {
    if (preferences != null) {
        DeveloperPreferencesList(
            preferences = preferences,
            onPrefersDarkThemeChanged = onPrefersDarkThemeChanged,
            onFavoriteDeviceLineChanged = onFavoriteDeviceLineChanged,
            onLoveForAndroidChanged = onLoveForAndroidChanged,
        )
    }

    if (showLoading) {
        CenteredProgressBar()
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun PreferenceListScreenPreview() {
    val preferences = DeveloperPreferences()
    val showLoading = false

    MVICaseStudyTheme {
        Surface {
            DeveloperPreferencesListScreen(
                preferences = preferences,
                showLoading = showLoading,
                onPrefersDarkThemeChanged = {},
                onFavoriteDeviceLineChanged = {},
                onLoveForAndroidChanged = {},
            )
        }
    }
}
