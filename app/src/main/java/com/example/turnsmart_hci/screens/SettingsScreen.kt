package com.example.turnsmart_hci.screens

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
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@Composable
fun SettingsScreen(
    notificationViewModel: NotificationViewModel
) {
    var selectedLanguage by remember { mutableStateOf("English") }
    var notificationsEnabled by remember { mutableStateOf(true) }

    TurnSmartTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LanguageSelector(
                selectedLanguage = selectedLanguage,
                onLanguageSelected = { language ->
                    selectedLanguage = language
                    notificationViewModel.setLanguage(language)
                }
            )

            NotificationsSwitch(
                notificationsEnabled = notificationsEnabled,
                onToggleNotifications = { enabled ->
                    notificationsEnabled = enabled
                    notificationViewModel.setNotificationsEnabled(enabled)
                }
            )
        }
    }
}

@Composable
fun LanguageSelector(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    val languages = listOf("English", "Spanish")

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Select Language:",
            modifier = Modifier.padding(bottom = 8.dp),
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp

        )
        languages.forEach { language ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                RadioButton(
                    selected = selectedLanguage == language,
                    onClick = {
                        onLanguageSelected(language)
                    }
                )
                Text(
                    text = language,
                    modifier = Modifier.padding(start = 8.dp),
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    onTextLayout = {}

                )
            }
        }
    }
}

@Composable
fun NotificationsSwitch(
    notificationsEnabled: Boolean,
    onToggleNotifications: (Boolean) -> Unit
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Notifications:",
            modifier = Modifier.weight(1f),
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            onTextLayout = {}

        )
        Switch(
            checked = notificationsEnabled,
            onCheckedChange = { isChecked ->
                onToggleNotifications(isChecked)
            }
        )
    }
}
