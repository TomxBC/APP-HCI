package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.window.Popup
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.ui.devices.LampViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_yellow

@Composable
fun LightButton(lamp: Lamp, lampViewModel: LampViewModel) {
    var showPopup by remember { mutableStateOf(false) }

    DeviceButton(
        label = lamp.name,
        onClick = { showPopup = true },
        backgroundColor = pale_yellow,
        icon = R.drawable.lights
    )
    if (showPopup) {
        Popup(
            onDismissRequest = {
                showPopup = false
            }){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
            ){
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(TurnSmartTheme.colors.background, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ){
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
                        onBackClick = { showPopup = false }
                    )
                }
            }
        }
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
    textColor: Color = TurnSmartTheme.colors.onPrimary,
    backgroundColor: Color = TurnSmartTheme.colors.background,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "Back",
                        tint = textColor,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

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
            Spacer(modifier = Modifier.height(16.dp).padding(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
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
            Spacer(modifier = Modifier.height(16.dp).padding(10.dp))
            Text(
                text = "Light Intensity:",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$lightIntensity%",
                color = textColor,
                fontSize = 25.sp,
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
                        tint = textColor
                    )
                }
                Spacer(modifier = Modifier.width(8.dp).padding(10.dp))
                Slider(
                    value = lightIntensity.toFloat(),
                    onValueChange = { newValue ->
                        onIntensityChange(newValue.toInt())
                    },
                    valueRange = 0f..100f,
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
                        tint = textColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp).padding(10.dp))
            Text(
                text = "Light Color",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp).padding(10.dp))
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