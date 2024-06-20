package com.example.turnsmart_hci

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.turnsmart_hci.screens.DevicesScreen
import com.example.turnsmart_hci.screens.FavoriteScreen
import com.example.turnsmart_hci.screens.SettingsScreen
import com.example.turnsmart_hci.navBars.TurnSmartToolbar
import com.example.turnsmart_hci.screens.Screens
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme


@Composable
fun MainScreen(
    viewModel: NotificationViewModel,
    layoutType: NavigationSuiteType,
    requestPermission: () -> Unit
) {
    val navController = rememberNavController()
    val currentScreenTitle = remember { mutableStateOf(Screens.Favorite.route) }
    val context = LocalContext.current

    TurnSmartTheme {
        var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.FAVORITES) }
        NavigationSuiteScaffold(
            layoutType = layoutType,
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        icon = {
                            Icon(
                                painterResource(
                                    if (it == currentDestination) it.iconFill else it.icon
                                ),
                                contentDescription = stringResource(it.contentDescription)
                            )
                        },
                        label = { Text(stringResource(it.label)) },
                        selected = it == currentDestination,
                        onClick = { currentDestination = it },
                        modifier = Modifier.padding(end=20.dp, top=10.dp, bottom=10.dp)
                    )
                }
            },
            containerColor = TurnSmartTheme.colors.primary,
            contentColor = TurnSmartTheme.colors.onPrimary,
            modifier = Modifier.padding(15.dp)
        ) {
            when (currentDestination) {
                AppDestinations.FAVORITES -> FavoriteScreen()
                AppDestinations.DEVICES -> DevicesScreen()
                AppDestinations.AUTOMATION -> AutomationScreen()
            }
        }
    }

}

enum class AppDestinations(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconFill: Int,
    @StringRes val contentDescription: Int
) {
    FAVORITES(R.string.favorite_label, R.drawable.favorite, R.drawable.favorite_fill, R.string.favorite_label),
    DEVICES(R.string.devices_label, R.drawable.devices,R.drawable.devices_fill, R.string.devices_label),
    AUTOMATION(R.string.automation_label, R.drawable.calendar, R.drawable.calendar_fill, R.string.automation_label)
}


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