package com.adammcneilly.mvicasestudy.core.ui.preferences

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.mvicasestudy.core.models.SliderPreference
import com.adammcneilly.mvicasestudy.core.ui.theme.MVICaseStudyTheme

/**
 * Given a [sliderPreference], configure a row for it to be displayed
 * on a preference screen.
 *
 * @param[onValueChanged] A callback to be invoked any time the user changes
 * the value of this slider.
 */
@Composable
fun SliderPreferenceRow(
    sliderPreference: SliderPreference,
    onValueChanged: (Float) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .semantics {
                preference = sliderPreference.preferenceName
            },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = sliderPreference.preferenceName,
                fontWeight = FontWeight.Bold,
            )

            Column {
                Text(
                    text = sliderPreference.selectedValue.toInt().toString(),
                )

                Slider(
                    value = sliderPreference.selectedValue,
                    onValueChange = onValueChanged,
                    valueRange = sliderPreference.range,
                    steps = sliderPreference.steps,
                )
            }
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
private fun SliderPreferenceRowPreview() {
    val selectedValue = remember {
        mutableStateOf(1F)
    }

    val preference = SliderPreference(
        preferenceName = "Slider Preference",
        range = 1F..10F,
        selectedValue = selectedValue.value,
        steps = 10,
    )

    MVICaseStudyTheme {
        Surface {
            SliderPreferenceRow(
                sliderPreference = preference,
                onValueChanged = { newValue ->
                    selectedValue.value = newValue
                },
            )
        }
    }
}
