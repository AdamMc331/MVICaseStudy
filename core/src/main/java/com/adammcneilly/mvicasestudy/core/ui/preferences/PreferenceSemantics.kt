package com.adammcneilly.mvicasestudy.core.ui.preferences

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

/**
 * This is a custom [SemanticsPropertyKey] that allows us to verify which preference is being set
 * onto a preference row.
 */
val PreferenceSemanticsKey = SemanticsPropertyKey<String>("Preference")
var SemanticsPropertyReceiver.preference by PreferenceSemanticsKey
