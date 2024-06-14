package com.example.turnsmart_hci

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.turnsmart.navBars.TurnSmartBottomNavigationBar
import com.example.turnsmart.screens.AutomationScreen
import com.example.turnsmart.screens.DevicesScreen
import com.example.turnsmart.screens.HomeScreen
import com.example.turnsmart.screens.SettingsScreen
import com.example.turnsmart_hci.navBars.TurnSmartToolbar
import com.example.turnsmart_hci.screens.Screens

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentScreenTitle = remember { mutableStateOf(Screens.Home.route) }

    Scaffold(
        topBar = { TurnSmartToolbar(navController = navController, currentScreenTitle = currentScreenTitle) },
        bottomBar = {
            if (navController.currentDestination?.route != Screens.Settings.route) {
                TurnSmartBottomNavigationBar(navController,  onTitleChange = { title ->
                    currentScreenTitle.value = title
                })
            }
        }
    ){ innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            MainNavHost(navController = navController)
        }
    }
}
@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            HomeScreen()
        }
        composable(Screens.Devices.route) {
            DevicesScreen()
        }
        composable(Screens.Automation.route) {
            AutomationScreen()
        }
        composable(Screens.Settings.route) {
            SettingsScreen()
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}