package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.ui.devices.LampViewModel
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_yellow

@Composable
fun LightButton(lamp: Lamp, lampViewModel: LampViewModel) {
    val showDialog = remember { mutableStateOf(false) }

    DeviceButton(
        label = lamp.name,
        onClick = { showDialog.value = true },
        backgroundColor = pale_yellow,
        icon = R.drawable.lights
    )
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = { Text(text = "Lights Control") },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "Close")
                }
            },
            text = {
                LightsScreen(
                    deviceName = lamp.name,
                    isOn = lamp.status == Status.ON,
                    onToggle = { isOn ->
                        if (isOn) {
                            lampViewModel.turnOn(lamp)
                        } else {
                            lampViewModel.turnOff(lamp)
                        }
                    },
                    lightIntensity = lamp.brightness,
                    onIntensityChange = { intensity ->
                        lampViewModel.setBrightness(lamp, intensity)
                    },
                    lightColor = Color.White,
                    onColorChange = { color ->
                        lampViewModel.setColor(lamp, "#${color.toArgb().and(0xFFFFFF).toString(16)}")
                    },
                    textColor = Color.Black
                )
            }
        )
    }
}

@Composable
fun LightsScreen(
    deviceName: String,
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    lightIntensity: Int,
    onIntensityChange: (Int) -> Unit,
    lightColor: Color,
    onColorChange: (Color) -> Unit,
    textColor: Color = Color.Black
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(pale_yellow, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.lights),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = deviceName,
                color = textColor,
                fontSize = 22.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isOn) "ON" else "OFF",
                    color = textColor,
                    fontSize = 16.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = isOn,
                    onCheckedChange = { isChecked ->
                        onToggle(isChecked)
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Light Intensity: $lightIntensity",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { if (lightIntensity > 0) onIntensityChange(lightIntensity - 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Slider(
                    value = lightIntensity.toFloat(),
                    onValueChange = { newValue ->
                        onIntensityChange(newValue.toInt())
                    },
                    valueRange = 0f..100f,
                    steps = 99,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { if (lightIntensity < 100) onIntensityChange(lightIntensity + 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Light Color",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            ColorSlider(
                selectedColor = lightColor,
                onColorSelected = onColorChange
            )
        }
    }
}

@Composable
fun ColorSlider(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(0f) }

    val colors = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Blue,
        Color.Magenta,
        Color.Red
    )

    val gradient = Brush.horizontalGradient(colors)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(selectedColor, shape = CircleShape)
                .border(2.dp, Color.Black, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(gradient, shape = RoundedCornerShape(8.dp))
        ) {
            Slider(
                value = sliderPosition,
                onValueChange = { position ->
                    sliderPosition = position
                    val colorIndex = (position * (colors.size - 1)).toInt()
                    onColorSelected(colors[colorIndex])
                },
                valueRange = 0f..1f,
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.Transparent,
                    inactiveTrackColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
    }
}





//@Preview
//@Composable
//fun ButtonPreview() {
//
//}