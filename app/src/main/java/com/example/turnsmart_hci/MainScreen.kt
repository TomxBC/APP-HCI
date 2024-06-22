package com.example.turnsmart_hci

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
<<<<<<< HEAD
import com.example.turnsmart_hci.screens.AutomationScreen
import com.example.turnsmart_hci.screens.DevicesScreen
import com.example.turnsmart_hci.screens.FavoriteScreen
import com.example.turnsmart_hci.screens.SettingsScreen
import com.example.turnsmart_hci.screens.Screens
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
=======
import com.example.turnsmart_hci.navBars.TurnSmartBottomNavigationBar
import com.example.turnsmart_hci.data.ui.devices.DevicesScreen
import com.example.turnsmart_hci.screens.HomeScreen
import com.example.turnsmart.screens.SettingsScreen
import com.example.turnsmart_hci.navBars.TurnSmartToolbar
import com.example.turnsmart_hci.screens.Screens
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
>>>>>>> api-integration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.example.turnsmart_hci.navBars.TurnSmartToolbar
=======
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turnsmart_hci.data.ui.devices.LampViewModel
import com.example.turnsmart_hci.data.ui.getViewModelFactory
import com.example.turnsmart_hci.devices.LightsScreen
>>>>>>> api-integration
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.screens.AutomationScreen
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme


@Composable
fun MainScreen(
    viewModel: NotificationViewModel,
    layoutType: NavigationSuiteType,
    requestPermission: () -> Unit
) {
    val navController = rememberNavController()
    var currentDestination by rememberSaveable { mutableStateOf(Screens.Favorite.route) }
    val context = LocalContext.current

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
<<<<<<< HEAD
            containerColor = TurnSmartTheme.colors.primary,
            contentColor = TurnSmartTheme.colors.onPrimary,
            modifier = Modifier.then(if (layoutType == NavigationSuiteType.NavigationDrawer) Modifier.padding(15.dp) else Modifier)
        ) {
            Scaffold(
                topBar ={
                    TurnSmartToolbar(navController)
                },
                floatingActionButton = {
                    // UI elements and logic to trigger notification
                    Button(onClick = {
                        requestPermission()
                    }) {
                        Text("Send Notification")
                    }
                },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(TurnSmartTheme.colors.primary)
                    ) {
//                        when (currentDestination) {
//                            AppDestinations.FAVORITES -> FavoriteScreen()
//                            AppDestinations.DEVICES -> DevicesScreen()
//                            AppDestinations.AUTOMATION -> AutomationScreen()
//                        }
                        MainNavHost(navController = navController)
                    }
=======
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.sendNotification(context)
                    },
                    modifier = Modifier.padding(16.dp) // Add padding to FAB
                ) {
                    Text("+",  onTextLayout = {})
>>>>>>> api-integration
                }
            )
        }
    }

}

val bottomBarItems = listOf(
    Screens.Favorite,
    Screens.Devices,
    Screens.Automation
)

//enum class AppDestinations(
//    @StringRes val label: Int,
//    @DrawableRes val icon: Int,
//    @DrawableRes val iconFill: Int,
//    @StringRes val contentDescription: Int
//) {
//    FAVORITES(R.string.favorite_label, R.drawable.favorite, R.drawable.favorite_fill, R.string.favorite_label),
//    DEVICES(R.string.devices_label, R.drawable.devices,R.drawable.devices_fill, R.string.devices_label),
//    AUTOMATION(R.string.automation_label, R.drawable.calendar, R.drawable.calendar_fill, R.string.automation_label),
//    SETTINGS(R.string.settings_label, R.drawable.settings, R.drawable.settings_fill, R.string.settings_label)
//}


@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.Favorite.route,
        modifier = modifier
    ) {
        composable(Screens.Favorite.route) {
            FavoriteScreen()
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