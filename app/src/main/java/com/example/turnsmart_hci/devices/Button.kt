package com.example.turnsmart_hci.devices

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.ThemeColors

@Composable
fun DeviceButton(
    label : String?,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = ThemeColors.DARK_TEXT.color,
    icon: Int,
    isOn: Boolean = false,
    power: (Boolean) -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .padding(8.dp)
            .size(width = 300.dp, height = 50.dp)
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(8.dp),
                clip = true
            ),
        enabled = enabled
    ) {
        var powerOn by remember { mutableStateOf(isOn) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 0.dp)
                    .size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label ?: "No se pudo obtener el nombre",
                color = textColor,
                fontSize = 18.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    powerOn = !powerOn
                    power(powerOn)
                },
                modifier = Modifier
                    .border(1.dp, if (powerOn) Color.White else Color.Black, CircleShape)
                    .background(if (powerOn) Color.Black else Color.White, CircleShape)
                    .size(26.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.power),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (powerOn) Color.White else Color.Black
                )
            }
        }
    }
}

