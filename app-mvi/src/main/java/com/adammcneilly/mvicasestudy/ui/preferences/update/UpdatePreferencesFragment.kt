package com.adammcneilly.mvicasestudy.ui.preferences.update

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.adammcneilly.mvicasestudy.core.ui.preferences.DeveloperPreferencesListScreen

/**
 * Unlike an actual Fragment, this class is just meant to be a composable wrapper for our
 * [DeveloperPreferencesListScreen]. This composable consumes a [viewModel], subscribes to its state,
 * and passes that state onto the screen composable.
 */
@Composable
fun UpdatePreferencesFragment(
    viewModel: UpdateDeveloperPreferencesViewModel,
) {
    val viewState = viewModel.viewState.collectAsState()

    DeveloperPreferencesListScreen(
        preferences = viewState.value.preferences,
        showLoading = viewState.value.showLoading,
        onPrefersDarkThemeChanged = viewModel::prefersDarkThemeChanged,
        onFavoriteDeviceLineChanged = viewModel::favoriteDeviceLineChanged,
        onLoveForAndroidChanged = viewModel::loveForAndroidChanged,
    )
}
