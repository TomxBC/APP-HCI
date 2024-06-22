package com.example.turnsmart_hci

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import android.Manifest
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.sendNotification(this,"working!!!!!", "Notification2")
        } else {
            // Handle permission denial (optional)
        }
    }

    private val viewModel: NotificationViewModel by viewModels()
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurnSmartTheme {
                Surface ( modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val viewModel by viewModels<NotificationViewModel>()
                    val adaptiveInfo = currentWindowAdaptiveInfo()
                    val layoutType = with(adaptiveInfo) {
                            if(windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED){
                                NavigationSuiteType.NavigationDrawer
                            }else{
                                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
                            }
                    }
                    MainScreen(viewModel, layoutType) { checkAndRequestPermission() }
                }
            }
        }
    }

    private fun checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                viewModel.sendNotification(this,"working!!!!!", "Notification1")
            }
        }
    }

}