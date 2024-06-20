package com.example.turnsmart_hci.navBars

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.turnsmart_hci.ui.theme.montserratFontFamily


@Composable
fun TurnSmartBottomNavigationBar(navController: NavHostController, modifier: Modifier = Modifier, onTitleChange: (String) -> Unit) {
    val selected = remember { mutableStateOf(Screens.Favorite.route) }

    NavigationBar(
        modifier = modifier,
        containerColor = TurnSmartTheme.colors.primary,
        contentColor = TurnSmartTheme.colors.onPrimary,
    ) {
        items.forEach { screen ->
            val title = stringResource(id = screen.title)
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(
                            if (selected.value == screen.route) screen.iconSelected else screen.icon
                        ),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        title,
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                },
                selected = selected.value == screen.route,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = TurnSmartTheme.colors.onTertiary
                ),
                onClick = {
                    selected.value = Screens.Favorite.route
                    navController.navigate(Screens.Favorite.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    onTitleChange(title)
                }
            )
        }
    }
}


val items = listOf(
    Screens.Favorite,
    Screens.Devices,
    Screens.Automation
)


@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    val largePadding = dimensionResource(R.dimen.large_padding)

    TurnSmartTheme {
        TurnSmartBottomNavigationBar(navController = navController, modifier = Modifier.padding(top = largePadding), onTitleChange = {title -> title})
        //TurnSmartNavigationRail(navController = navController, modifier = Modifier.padding(top = largePadding), onTitleChange = {title -> title})
        //TurnSmartNavigationDrawer(navController = navController, modifier = Modifier.padding(top = largePadding), onTitleChange = {title -> title})
    }
}

