package com.adammcneilly.mvicasestudy.core.ui.preferences

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.mvicasestudy.core.models.SwitchPreference
import com.adammcneilly.mvicasestudy.core.ui.theme.MVICaseStudyTheme

/**
 * Given a [switchPreference], configure the UI for this preference
 * to be displayed in a preference screen.
 *
 * @param[onCheckedChanged] A callback that will be invoked any time the user
 * changes the toggle of the switch.
 */
@Composable
fun SwitchPreferenceRow(
    switchPreference: SwitchPreference,
    onCheckedChanged: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .semantics {
                preference = switchPreference.preferenceName
            },
    ) {
        Text(
            text = switchPreference.preferenceName,
            modifier = Modifier
                .weight(1F),
        )

        Switch(
            checked = switchPreference.isChecked,
            onCheckedChange = onCheckedChanged,
        )
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
private fun SwitchPreferenceRowPreview() {
    val isChecked = remember {
        mutableStateOf(false)
    }

    val preference = SwitchPreference(
        preferenceName = "Switch Preference",
        isChecked = isChecked.value,
    )

    MVICaseStudyTheme {
        Surface {
            SwitchPreferenceRow(
                switchPreference = preference,
                onCheckedChanged = { checked ->
                    isChecked.value = checked
                }
            )
        }
    }
}
