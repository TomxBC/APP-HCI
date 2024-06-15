package com.example.turnsmart_hci.navBars

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.screens.Screens
import com.example.turnsmart_hci.ui.theme.lightText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurnSmartToolbar(navController: NavController, currentScreenTitle: MutableState<String>) {
    TopAppBar(
        title = { Text(text = currentScreenTitle.value) },
        navigationIcon = {
            if (navController.currentDestination?.route == Screens.Settings.route) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null,
                        tint = lightText
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = lightText
        ),
        actions = {
            if (navController.currentDestination?.route != Screens.Settings.route) {
                IconButton(onClick = {
                    navController.navigate(Screens.Settings.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(id = R.string.settings_label),
                        tint = lightText
                    )
                }
            }
        }
    )

}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewTurnSmartToolbar() {
    val navController = rememberNavController()
    val currentScreenTitle = mutableStateOf("Settings")

    TurnSmartToolbar(navController, currentScreenTitle)

}