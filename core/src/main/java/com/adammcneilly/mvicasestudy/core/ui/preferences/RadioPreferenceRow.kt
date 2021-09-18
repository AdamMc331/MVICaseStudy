package com.adammcneilly.mvicasestudy.core.ui.preferences

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.mvicasestudy.core.models.RadioPreference
import com.adammcneilly.mvicasestudy.core.ui.theme.MVICaseStudyTheme

val RadioOptionKey = SemanticsPropertyKey<String>("RadioOption")
var SemanticsPropertyReceiver.radioOption by RadioOptionKey

/**
 * Given a [radioPreference], configure the UI to be shown in a preference screen.
 *
 * @param[optionClicked] A callback for when one of the options on the [radioPreference]
 * has been selected.
 */
@Composable
fun RadioPreferenceRow(
    radioPreference: RadioPreference,
    optionClicked: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .semantics {
                preference = radioPreference.preferenceName
            },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = radioPreference.preferenceName,
                fontWeight = FontWeight.Bold,
            )

            PreferenceOptionRow(radioPreference, optionClicked)
        }
    }
}

/**
 * Given a [radioPreference], loop through all of the options and add the corresponding
 * buttons to the UI.
 */
@Composable
private fun PreferenceOptionRow(
    radioPreference: RadioPreference,
    optionClicked: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        radioPreference.options.forEach { option ->
            RadioOption(
                option = option,
                isChecked = (option == radioPreference.selectedOption),
                onClick = {
                    optionClicked.invoke(option)
                },
            )
        }
    }
}

/**
 * Given an [option], and whether or not it [isChecked], add that [RadioButton] to
 * the screen.
 *
 * @param[onClick] A callback for any time the user selects this option.
 */
@Composable
private fun RadioOption(
    option: String,
    isChecked: Boolean,
    onClick: () -> Unit,
) {
    Row {
        Text(
            text = option,
        )

        RadioButton(
            selected = isChecked,
            onClick = onClick,
            modifier = Modifier
                .padding(start = 8.dp)
                .semantics {
                    this.radioOption = option
                },
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
private fun RadioPreferenceRowPreview() {
    val options = listOf("One", "Two", "Three")

    val selectedOption = remember {
        mutableStateOf(options.first())
    }

    val preference = RadioPreference(
        preferenceName = "Radio Preference",
        options = options,
        selectedOption = selectedOption.value,
    )

    MVICaseStudyTheme {
        Surface {
            RadioPreferenceRow(
                radioPreference = preference,
                optionClicked = { option ->
                    selectedOption.value = option
                }
            )
        }
    }
}
