package com.example.turnsmart_hci.navBars

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.screens.Screens
import com.example.turnsmart_hci.screens.getScreen
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.lightText
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurnSmartToolbar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val previousScreenTitle = remember { mutableStateOf("") }

    TopAppBar(
        title = {
            if (currentDestination != null) {
                val currentScreen = getScreen(currentDestination)
                Text(text = currentDestination, color = Color.White, fontFamily = montserratFontFamily, fontWeight = FontWeight.Medium)
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
                        tint = Color.White
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

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewTurnSmartToolbar() {
    val navController = rememberNavController()
    val currentScreenTitle = mutableStateOf("Settings")

    //TurnSmartToolbar(navController, currentScreenTitle)

}