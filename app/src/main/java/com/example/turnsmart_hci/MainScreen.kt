package com.example.turnsmart_hci

//import androidx.compose.runtime.Composable
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.*
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.turnsmart_hci.R
////import com.example.turnsmart_hci.navBars.TurnSmartBottomNavigationBar
//import com.example.turnsmart_hci.ui.screens.*

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    var topBarTitle by remember { mutableStateOf("Home") }
//    var showBottomNav by remember { mutableStateOf(true) }
//    var enableBackButton by remember { mutableStateOf(false) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(topBarTitle) },
//                navigationIcon = {
//                    if (enableBackButton) {
//                        IconButton(onClick = { navController.navigateUp() }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.back_arrow),
//                                contentDescription = null
//                            )
//                        }
//                    } else null
//                },
//                actions = {
//                    if (topBarTitle != "Settings") {
//                        IconButton(onClick = {
//                            navController.navigate("settings")
//                            topBarTitle = "Settings"
//                        }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.settings),
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
//            )
//        },
//        bottomBar = {
//            if (showBottomNav) {
//                //TurnSmartBottomNavigationBar()
//            }
//        },
//        content = { padding ->
//            Box(modifier = Modifier.padding(padding)) {
//                NavHost(
//                    navController = navController,
//                    startDestination = "home"
//                ) {
//                    composable("home") {
//                        HomeScreen()
//                        LaunchedEffect(Unit) {
//                            topBarTitle = "Home"
//                            showBottomNav = true
//                            enableBackButton = false
//                        }
//                    }
//                    composable("devices") {
//                        DevicesScreen()
//                        LaunchedEffect(Unit) {
//                            topBarTitle = "Devices"
//                            showBottomNav = true
//                            enableBackButton = false
//                        }
//                    }
//                    composable("automation") {
//                        AutomationScreen()
//                        LaunchedEffect(Unit) {
//                            topBarTitle = "Automation"
//                            showBottomNav = true
//                            enableBackButton = false
//                        }
//                    }
//                    composable("settings") {
//                        SettingsScreen()
//                        LaunchedEffect(Unit) {
//                            topBarTitle = "Settings"
//                            showBottomNav = false
//                            enableBackButton = true
//                        }
//                    }
//                }
//            }
//        }
//    )
//}
