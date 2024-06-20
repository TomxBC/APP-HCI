package com.example.turnsmart_hci.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.tabletVersion.TabletDeviceButton
import com.example.turnsmart_hci.ui.theme.ThemeColors
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.dark_purple
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@Composable
fun DevicesScreen() {
    TurnSmartTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Devices Screen", fontFamily = montserratFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 30.sp, color = dark_purple)
            }
            TabletDeviceButton(
                label = R.string.lights, onClick = {},
                icon = R.drawable.lights, backgroundColor = ThemeColors.PALE_YELLOW.color,
                status = "Off"
            )
        }
    }

}