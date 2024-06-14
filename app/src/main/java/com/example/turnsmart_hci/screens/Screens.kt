package com.example.turnsmart_hci.screens

// Is the nav_graph
sealed class Screens(val route: String, val title: String) {
    object Home : Screens("home", "Home")
    object Devices : Screens("devices", "Devices")
    object Automation : Screens("automations", "Automation")
    object Settings : Screens("settings", "Settings")
}
