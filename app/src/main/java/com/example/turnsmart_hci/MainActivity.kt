package com.example.turnsmart_hci

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import android.Manifest
import android.content.Context
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Send notifications and show allowed message
            NotificationViewModel().sendActivatedNotifications(
                this,
                R.string.notification_title_allowed_devices,
                R.string.notification_title_allowed_routines,
                R.string.notification_title_allowed)
        } else {
            // Show a dialog explaining that notifications need to be enabled in settings
            showToast(this, "Permission denied. Activate them in device's settings.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurnSmartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = TurnSmartTheme.colors.background
                ) {
                    val viewModel: NotificationViewModel = viewModel()
                    val adaptiveInfo = currentWindowAdaptiveInfo()
                    val layoutType = with(adaptiveInfo) {
                        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
                            NavigationSuiteType.NavigationDrawer
                        } else {
                            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
                        }
                    }
                    MainScreen(viewModel, layoutType) {
                        checkAndRequestPermission()
                    }
                    checkAndRequestPermission()
                }
            }
        }
    }

    @Composable
    private fun checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(
                    LocalContext.current,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission not granted, request permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                // Permission already granted
                NotificationViewModel().sendActivatedNotifications(
                    this,
                    R.string.notification_title_allowed_devices,
                    R.string.notification_title_allowed_routines,
                    R.string.notification_title_allowed)
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }