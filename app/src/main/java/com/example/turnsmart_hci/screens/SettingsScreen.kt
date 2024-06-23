package com.example.turnsmart_hci.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@Composable
fun SettingsScreen(
    notificationViewModel: NotificationViewModel
) {
    TurnSmartTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "To change the app language, please adjust the language settings on your device.",
                modifier = Modifier.padding(start = 8.dp),
                fontFamily = montserratFontFamily,
                fontSize = 16.sp
            )

            // Existing notifications message
            Text(
                text = "To activate notifications please access your device's settings",
                modifier = Modifier.padding(start = 8.dp),
                fontFamily = montserratFontFamily,
                fontSize = 16.sp
            )
        }
    }
}
