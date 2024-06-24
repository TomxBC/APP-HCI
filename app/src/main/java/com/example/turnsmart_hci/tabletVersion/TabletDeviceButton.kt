package com.example.turnsmart_hci.tabletVersion

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.Device
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.ThemeColors

@Composable
fun TabletDeviceButton(
    label : String?,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = ThemeColors.DARK_TEXT.color,
    icon: Int,
    isOn: Boolean = false,
    power: (Boolean) -> Unit,
    status: String,
    device: Device,
    onToggleFavorite: (String) -> Unit // Añade esta línea
) {
    var isFav by remember { mutableStateOf(device.favorite) }
    var powerOn by remember { mutableStateOf(isOn) }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .padding(8.dp)
            .size(250.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = {
                    isFav = !isFav
                    onToggleFavorite(device.id) // Llama a esta función
                },
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Icon(
                    painter = painterResource(
                        if (isFav) R.drawable.favorite_fill else R.drawable.favorite
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = {
                    powerOn = !powerOn
                    power(powerOn)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp)
                    .border(2.dp, if (powerOn) Color.White else Color.Black, CircleShape)
                    .background(if (powerOn) Color.Black else Color.White, CircleShape),
            ) {
                Icon(
                    painter = painterResource(R.drawable.power),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = if (powerOn) Color.White else Color.Black
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .size(60.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = label ?: "no label",
                    color = textColor,
                    fontSize = 25.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = status,
                color = textColor,
                fontSize = 20.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp)
            )
        }
    }
}
