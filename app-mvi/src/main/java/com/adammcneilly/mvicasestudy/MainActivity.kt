package com.adammcneilly.mvicasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adammcneilly.mvicasestudy.core.ui.theme.MVICaseStudyTheme
import com.adammcneilly.mvicasestudy.ui.HomeScreen
import com.adammcneilly.mvicasestudy.ui.preferences.read.ReadDeveloperPreferencesViewModel
import com.adammcneilly.mvicasestudy.ui.preferences.read.ReadPreferencesFragment
import com.adammcneilly.mvicasestudy.ui.preferences.update.UpdateDeveloperPreferencesViewModel
import com.adammcneilly.mvicasestudy.ui.preferences.update.UpdatePreferencesFragment

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val readViewModel =
            ViewModelProvider(this).get(ReadDeveloperPreferencesViewModel::class.java)

        val updateViewModel =
            ViewModelProvider(this).get(UpdateDeveloperPreferencesViewModel::class.java)

        setContent {
            MVICaseStudyTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("MVI Case Study - MVI Module")
                            }
                        )
                    }
                ) {
                    NavHost(navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                onReadPreferencesClicked = {
                                    navController.navigate("readPreferences")
                                },
                                onUpdatePreferencesClicked = {
                                    navController.navigate("updatePreferences")
                                },
                            )
                        }

                        composable("readPreferences") {
                            ReadPreferencesFragment(viewModel = readViewModel)
                        }

                        composable("updatePreferences") {
                            UpdatePreferencesFragment(viewModel = updateViewModel)
                        }
                    }
                }
            }
        }
    }
}
