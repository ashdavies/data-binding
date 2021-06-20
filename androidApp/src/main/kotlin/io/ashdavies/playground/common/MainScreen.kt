package io.ashdavies.playground.common

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.BottomNavigation
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.ashdavies.playground.R
import io.ashdavies.playground.Route
import io.ashdavies.playground.Route.Conferences
import io.ashdavies.playground.Route.Profile
import io.ashdavies.playground.compose.NavHost
import io.ashdavies.playground.compose.composable
import io.ashdavies.playground.compose.navigate
import io.ashdavies.playground.compose.routeAsState
import io.ashdavies.playground.conferences.ConferencesScreen
import io.ashdavies.playground.profile.ProfileScreen

@Composable
internal fun MainScreen() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
        )
    }

    PlaygroundTheme {
        ProvideWindowInsets {
            val navController: NavHostController = rememberNavController()
            val currentRoute by navController.routeAsState(Conferences)

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(currentRoute.title)) },
                        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.95f),
                        contentPadding = rememberInsetsPaddingValues(
                            LocalWindowInsets.current.statusBars,
                            applyBottom = false,
                        ),
                    )
                },

                bottomBar = {
                    var selected by remember { mutableStateOf(0) }

                    BottomNavigation(
                        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.95f),
                        contentPadding = rememberInsetsPaddingValues(
                            LocalWindowInsets.current.navigationBars
                        )
                    ) {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Home, "Home") },
                            selected = selected == 0,
                            onClick = {
                                navController.navigate(Conferences)
                                selected = 0
                            }
                        )
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Person, "Profile") },
                            selected = selected == 1,
                            onClick = {
                                navController.navigate(Profile)
                                selected = 1
                            }
                        )
                    }
                },
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Conferences,
                ) {
                    composable(Conferences) { ConferencesScreen() }
                    composable(Profile) { ProfileScreen() }
                }
            }
        }
    }
}

private val Route.title: Int
    @StringRes get() = when (this) {
        is Conferences -> R.string.upcoming
        is Profile -> R.string.profile
    }