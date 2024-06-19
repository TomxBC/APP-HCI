package com.example.turnsmart_hci

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.turnsmart_hci.navBars.TurnSmartBottomNavigationBar
import com.example.turnsmart_hci.screens.AutomationScreen
import com.example.turnsmart_hci.data.ui.devices.DevicesScreen
import com.example.turnsmart_hci.screens.HomeScreen
import com.example.turnsmart_hci.screens.SettingsScreen
import com.example.turnsmart_hci.navBars.TurnSmartToolbar
import com.example.turnsmart_hci.screens.Screens
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme

@Composable
fun MainScreen(
    viewModel: NotificationViewModel
) {
    val navController = rememberNavController()
    val currentScreenTitle = remember { mutableStateOf(Screens.Home.route) }
    val context = LocalContext.current

    TurnSmartTheme {
        Scaffold(
            topBar = { TurnSmartToolbar(navController = navController, currentScreenTitle = currentScreenTitle) },
            bottomBar = {
                if (navController.currentDestination?.route != Screens.Settings.route) {
                    TurnSmartBottomNavigationBar(navController, onTitleChange = { title ->
                        currentScreenTitle.value = title
                    })
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.sendNotification(context)
                    },
                    modifier = Modifier.padding(16.dp) // Add padding to FAB
                ) {
                    Text("+")
                }
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    MainNavHost(navController = navController)
                }
            }
        )
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
    val viewModel = NotificationViewModel()
    MainScreen(viewModel)
}