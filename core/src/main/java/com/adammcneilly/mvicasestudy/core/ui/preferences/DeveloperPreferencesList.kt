package com.adammcneilly.mvicasestudy.core.ui.preferences

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.mvicasestudy.core.models.DeveloperPreferences
import com.adammcneilly.mvicasestudy.core.models.RadioPreference
import com.adammcneilly.mvicasestudy.core.models.SliderPreference
import com.adammcneilly.mvicasestudy.core.models.SwitchPreference
import com.adammcneilly.mvicasestudy.core.ui.theme.MVICaseStudyTheme

/**
 * This composable consumes a [DeveloperPreferences] entity and formats all of them into a
 * [LazyColumn].
 *
 * Any changes to preferences are exposed via the various lambdas supplied.
 */
@Composable
fun DeveloperPreferencesList(
    preferences: DeveloperPreferences,
    onPrefersDarkThemeChanged: (Boolean) -> Unit,
    onFavoriteDeviceLineChanged: (String) -> Unit,
    onLoveForAndroidChanged: (Int) -> Unit,
) {
    val darkThemePreference = SwitchPreference(
        preferenceName = "Prefers Dark Theme",
        isChecked = preferences.prefersDarkTheme,
    )

    val deviceLinePreference = RadioPreference(
        preferenceName = "Favorite Device Line",
        options = listOf("Pixel", "Galaxy", "Other"),
        selectedOption = preferences.favoriteDeviceLine,
    )

    val loveForAndroidPreference = SliderPreference(
        preferenceName = "Love For Android",
        range = 1F..10F,
        selectedValue = preferences.loveForAndroid.toFloat(),
        steps = 10,
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        item {
            SwitchPreferenceRow(
                switchPreference = darkThemePreference,
                onCheckedChanged = onPrefersDarkThemeChanged,
            )

            Divider()
        }

        item {
            RadioPreferenceRow(
                radioPreference = deviceLinePreference,
                optionClicked = onFavoriteDeviceLineChanged,
            )

            Divider()
        }

        item {
            SliderPreferenceRow(
                sliderPreference = loveForAndroidPreference,
                onValueChanged = { value ->
                    onLoveForAndroidChanged.invoke(value.toInt())
                },
            )

            Divider()
        }
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
private fun DeveloperPreferencesListPreview() {
    val preferences = DeveloperPreferences()

    MVICaseStudyTheme {
        Surface {
            DeveloperPreferencesList(
                preferences = preferences,
                onPrefersDarkThemeChanged = {},
                onFavoriteDeviceLineChanged = {},
                onLoveForAndroidChanged = {},
            )
        }
    }
}
