package io.ashdavies.playground.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ashdavies.playground.R
import io.ashdavies.playground.Route.Conferences
import io.ashdavies.playground.conferences.ConferencesScreen

@Preview
@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()

    LocalSystemUi
        .current
        .setStatusBarColor(MaterialTheme.colors.primaryVariant)

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.application)) },
                    actions = {
                        IconButton(onClick = { TODO() }) {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_search_24),
                                contentDescription = stringResource(R.string.search),
                            )
                        }
                    },
                )
            },
            bottomBar = {
                BottomAppBar {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            modifier = Modifier.align(CenterHorizontally),
                            onClick = { TODO() },
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_code_24),
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Conferences,
            ) {
                composable(Conferences) { ConferencesScreen() }
            }
        }
    }
}
