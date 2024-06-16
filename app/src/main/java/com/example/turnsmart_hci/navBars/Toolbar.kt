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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.screens.Screens
import com.example.turnsmart_hci.ui.theme.lightText
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurnSmartToolbar(navController: NavController, currentScreenTitle: MutableState<String>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val previousScreenTitle = remember { mutableStateOf("") }

    TopAppBar(
        title = { Text(text = currentScreenTitle.value, fontFamily = montserratFontFamily, fontWeight = FontWeight.Medium) },
        navigationIcon = {
            if (currentDestination == Screens.Settings.route) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                        currentScreenTitle.value = previousScreenTitle.value
                    }
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null,
                        tint = Color.White
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
                        navController.previousBackStackEntry?.destination?.id?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    previousScreenTitle.value = currentScreenTitle.value
                    currentScreenTitle.value = "Settings"
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