package com.example.turnsmart.navBars

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import androidx.compose.material3.*
import androidx.navigation.NavHostController
import com.example.turnsmart_hci.screens.Screens
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.turnsmart_hci.ui.theme.lightBottomAppBar
import com.example.turnsmart_hci.ui.theme.lightText
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_purple

@Composable
fun TurnSmartBottomNavigationBar(navController: NavHostController, modifier: Modifier = Modifier, onTitleChange: (String) -> Unit) {
    val selected = remember { mutableStateOf(Screens.Home.route) }

    NavigationBar(
        modifier = modifier,
        containerColor = lightBottomAppBar,
        contentColor = lightText
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(
                        if (selected.value == Screens.Home.route) R.drawable.home_fill else R.drawable.home
                    ),
                    contentDescription = stringResource(id = R.string.home_label)
                )
            },
            label = {
                Text(stringResource(R.string.home_label), fontFamily = montserratFontFamily, fontWeight = FontWeight.Medium)
            },
            selected = selected.value == Screens.Home.route,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = pale_purple
            ),
            onClick = {
                selected.value = Screens.Home.route
                navController.navigate(Screens.Home.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onTitleChange(Screens.Home.title)
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(
                        if (selected.value == Screens.Devices.route) R.drawable.devices_fill else R.drawable.devices
                    ),
                    contentDescription = stringResource(id = R.string.devices_label)
                )
            },
            label = {
                Text(stringResource(R.string.devices_label), fontFamily = montserratFontFamily, fontWeight = FontWeight.Medium)
            },
            selected = selected.value == Screens.Devices.route,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = pale_purple
            ),
            onClick = {
                selected.value = Screens.Devices.route
                navController.navigate(Screens.Devices.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onTitleChange(Screens.Devices.title)
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(
                        if (selected.value == Screens.Automation.route) R.drawable.calendar_fill else R.drawable.calendar
                    ),
                    contentDescription = stringResource(id = R.string.automation_label)
                )
            },
            label = {
                Text(stringResource(R.string.automation_label), fontFamily = montserratFontFamily, fontWeight = FontWeight.Medium)
            },
            selected = selected.value == Screens.Automation.route,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = pale_purple
            ),
            onClick = {
                selected.value = Screens.Automation.route
                navController.navigate(Screens.Automation.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onTitleChange(Screens.Automation.title)
            }
        )
    }
}





@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    val largePadding = dimensionResource(R.dimen.large_padding)

    TurnSmartTheme {
        TurnSmartBottomNavigationBar(navController = navController, modifier = Modifier.padding(top = largePadding), onTitleChange = {title -> title})
    }
}

