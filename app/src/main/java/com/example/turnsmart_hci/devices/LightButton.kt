package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.turnsmart_hci.ui.theme.pale_yellow
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@Composable
fun LightButton() {
    DeviceButton(
        label = R.string.lights,
        onClick = {},
        backgroundColor = pale_yellow,
        icon = R.drawable.lights
    )
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
            .background(Color.White, shape = RoundedCornerShape(8.dp))
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
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Off/On",
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
                fontWeight = FontWeight.Medium,
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
                    steps = 99, // Ensures the slider snaps to integer values
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


@Composable
fun LightsControlScreen() {
    var isOn by remember { mutableStateOf(false) }
    var lightIntensity by remember { mutableStateOf(50) }
    var lightColor by remember { mutableStateOf(Color.White) }

    LightsScreen(
        deviceName = "Living Room Light",
        isOn = isOn,
        onToggle = { isOn = it },
        lightIntensity = lightIntensity,
        onIntensityChange = { lightIntensity = it },
        lightColor = lightColor,
        onColorChange = { lightColor = it },
    )
}

@Preview
@Composable
fun ButtonPreview() {
    LightButton()
    LightsControlScreen()
}