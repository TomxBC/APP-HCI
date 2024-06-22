package com.example.turnsmart_hci.screens

<<<<<<< HEAD
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.turnsmart_hci.R

sealed class Screens(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int, @DrawableRes val iconSelected: Int ) {
    object Favorite : Screens("favorite", R.string.favorite_label, R.drawable.favorite, R.drawable.favorite_fill)
    object Devices : Screens("devices", R.string.devices_label, R.drawable.devices, R.drawable.devices_fill)
    object Automation : Screens("automations", R.string.automation_label, R.drawable.calendar, R.drawable.calendar_fill)
    object Settings : Screens("settings", R.string.settings_label, R.drawable.settings, R.drawable.settings)
}

fun getScreen(route: String?){
    when(route){
        Screens.Favorite.route -> Screens.Favorite.title
        Screens.Devices.route -> Screens.Devices.title
        Screens.Automation.route -> Screens.Automation.title
        Screens.Settings.route -> Screens.Settings.title
        else -> Screens.Favorite.title
    }
=======
// Is the nav_graph
sealed class Screens(val route: String, val title: String) {
    object Home : Screens("home", "Home")
    object Devices : Screens("devices", "Devices")
    object Automation : Screens("automations", "Automation")
    object Settings : Screens("settings", "Settings")

    object Lights : Screens("lights", "Lights")
>>>>>>> api-integration
}
