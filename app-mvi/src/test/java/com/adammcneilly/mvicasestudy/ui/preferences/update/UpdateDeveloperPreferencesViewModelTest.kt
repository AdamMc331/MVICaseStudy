package com.adammcneilly.mvicasestudy.ui.preferences.update

import com.adammcneilly.mvicasestudy.CoroutineTestRule
import com.adammcneilly.mvicasestudy.domain.update.UpdatePreferencesAction
import org.junit.Rule
import org.junit.Test

/**
 * This runs a series of unit tests to ensure the [UpdateDeveloperPreferencesViewModel] behaves as expected.
 */
class UpdateDeveloperPreferencesViewModelTest {
    private val testRobot = UpdateDeveloperPreferencesViewModelRobot()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Test
    fun fetchPreferencesWhenCreated() {

        testRobot
            .buildViewModel()
            .assertActionDispatched(UpdatePreferencesAction.FetchPreferences)
    }

    @Test
    fun updatePrefersDarkTheme() {
        val prefersDarkTheme = true

        testRobot
            .buildViewModel()
            .changePrefersDarkTheme(prefersDarkTheme)
            .assertActionDispatched(
                expectedAction = UpdatePreferencesAction.UpdatePrefersDarkTheme(prefersDarkTheme)
            )
    }

    @Test
    fun updateFavoriteDeviceLine() {
        val favoriteDeviceLine = "Pixel"

        testRobot
            .buildViewModel()
            .changeFavoriteDeviceLine(favoriteDeviceLine)
            .assertActionDispatched(
                expectedAction = UpdatePreferencesAction.UpdateFavoriteDeviceLine(favoriteDeviceLine)
            )
    }

    @Test
    fun updateLoveForAndroid() {
        val loveForAndroid = 100

        testRobot
            .buildViewModel()
            .changeLoveForAndroid(loveForAndroid)
            .assertActionDispatched(
                expectedAction = UpdatePreferencesAction.UpdateLoveForAndroid(loveForAndroid)
            )
    }
}
