package com.example.turnsmart_hci.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
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
                text = stringResource(id = R.string.change_language),
                modifier = Modifier.padding(start = 8.dp),
                fontFamily = montserratFontFamily,
                fontSize = 16.sp
            )

            // Existing notifications message
            Text(
                text = stringResource(id = R.string.change_notifs),
                modifier = Modifier.padding(start = 8.dp),
                fontFamily = montserratFontFamily,
                fontSize = 16.sp
            )
        }
    }
}
