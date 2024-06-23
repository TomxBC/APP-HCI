package com.example.turnsmart_hci.navBars


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.screens.Screens
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurnSmartToolbar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            if (currentDestination != null) {
                val screenTitleResourceId = getScreenTitle(currentDestination)
                Text(
                    text = stringResource(id = screenTitleResourceId),
                    color = TurnSmartTheme.colors.onSecondary,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        navigationIcon = {
            if (currentDestination == Screens.Settings.route) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null,
                        tint = TurnSmartTheme.colors.onSecondary
                    )

                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TurnSmartTheme.colors.secondary,
            titleContentColor = TurnSmartTheme.colors.onSecondary
        ),
        actions = {
            if (navController.currentDestination?.route != Screens.Settings.route) {
                IconButton(onClick = {
                    navController.navigate(Screens.Settings.route)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(id = R.string.settings_label),
                        tint = TurnSmartTheme.colors.onSecondary
                    )
                }
            }
        }
    )

}

fun getScreenTitle(route: String): Int {
    return when (route) {
        "favorite"-> Screens.Favorite.title
        "settings"-> Screens.Settings.title
        "automations"-> Screens.Automation.title
        "devices" -> Screens.Devices.title
        else -> Screens.Favorite.title
    }
}