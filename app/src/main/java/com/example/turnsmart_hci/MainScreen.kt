package com.example.turnsmart_hci

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.turnsmart_hci.screens.AutomationScreen
import com.example.turnsmart_hci.screens.DevicesScreen
import com.example.turnsmart_hci.screens.SettingsScreen
import com.example.turnsmart_hci.screens.Screens
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import com.example.turnsmart_hci.navBars.TurnSmartToolbar

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme

val bottomBarItems = listOf(
    Screens.Devices,
    Screens.Automation
)

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    notificationViewModel: NotificationViewModel,
    layoutType: NavigationSuiteType
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Devices.route,
        modifier = modifier
    ) {
        composable(Screens.Devices.route) {
            DevicesScreen(
                notificationViewModel=notificationViewModel,
                layoutType = layoutType
            )
        }
        composable(Screens.Automation.route) {
            AutomationScreen(
                notificationViewModel=notificationViewModel,
                layoutType = layoutType
            )
        }
        composable(Screens.Settings.route) {
            SettingsScreen(notificationViewModel)
        }

    }
}

@Composable
fun MainScreen(
    notificationViewModel: NotificationViewModel,
    layoutType: NavigationSuiteType,
    requestPermission: @Composable () -> Unit
) {
    val navController = rememberNavController()
    var currentDestination by rememberSaveable { mutableStateOf(Screens.Devices.route) }
    val paddingModifier = when (layoutType) {
        NavigationSuiteType.NavigationDrawer -> Modifier.padding(15.dp)
        NavigationSuiteType.NavigationRail -> Modifier.padding(top = 15.dp, start = 15.dp)
        else -> Modifier
    }

    TurnSmartTheme {
        NavigationSuiteScaffold(
            layoutType = layoutType,
            navigationSuiteItems = {
                bottomBarItems.forEach {
                    item(
                        icon = {
                            Icon(
                                painterResource(
                                    if (it.route == currentDestination) it.iconSelected else it.icon
                                ),
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(it.title)) },
                        selected = it.route == currentDestination,
                        onClick = {
                                    navController.navigate(it.route)
                                    currentDestination = it.route
                                  },
                        modifier = Modifier.padding(end=20.dp, top=10.dp, bottom=10.dp)
                    )
                }
            },
            containerColor = TurnSmartTheme.colors.primary,
            contentColor = TurnSmartTheme.colors.onPrimary,
            modifier = Modifier.then(paddingModifier)
        ) {
            Scaffold(
                topBar ={
                    TurnSmartToolbar(navController)
                },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(TurnSmartTheme.colors.background)
                    ) {
                        MainNavHost(
                            navController = navController,
                            notificationViewModel= notificationViewModel,
                            layoutType = layoutType,
                        )
                    }
                }
            )
        }
    }
}


