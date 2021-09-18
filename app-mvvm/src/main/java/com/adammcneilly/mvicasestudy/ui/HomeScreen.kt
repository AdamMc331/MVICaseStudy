package com.adammcneilly.mvicasestudy.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * This is just a composable with two buttons that allows the user to navigate to the read
 * preferences screen or update preferences screen.
 */
@Composable
fun HomeScreen(
    onReadPreferencesClicked: () -> Unit,
    onUpdatePreferencesClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Button(
            onClick = onReadPreferencesClicked,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text("Read Preferences")
        }

        Button(
            onClick = onUpdatePreferencesClicked,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text("Update Preferences")
        }
    }
}
