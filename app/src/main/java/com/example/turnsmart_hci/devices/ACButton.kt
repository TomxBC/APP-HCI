package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@Composable
fun AirConditionerScreen(
    deviceName: String,
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    temperature: Int,
    onSetTemperature: (Int) -> Unit,
    mode: String,
    onSetMode: (String) -> Unit,
    verticalSwing: Int,
    onSetVerticalSwing: (Int) -> Unit,
    horizontalSwing: Int,
    onSetHorizontalSwing: (Int) -> Unit,
    fanSpeed: Int,
    onSetFanSpeed: (Int) -> Unit,
    textColor: Color = Color.Black
) {
    val modes = listOf("Fan", "Cooling", "Heating")
    var expanded by remember { mutableStateOf(false) }
    val horizontalSwingPositions = listOf("-90°", "-45°", "0°", "45°", "90°")
    val verticalSwingPositions = listOf("Auto", "0°", "22°","45°","67°","90°")
    val fanSpeedPositions = listOf("Auto", "25%", "50%", "75%", "100%")

    Box(modifier = Modifier.verticalScroll(rememberScrollState())
        .fillMaxWidth()
        .background(Color.White, shape = RoundedCornerShape(8.dp))
        .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ac),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = deviceName,
                color = textColor,
                fontSize = 20.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))

            //on/ off
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

            //Modes
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Mode:",
                    color = textColor,
                    fontSize = 16.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Box {
                    Text(
                        text = mode,
                        color = textColor,
                        fontSize = 16.sp,
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { expanded = !expanded }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        modes.forEach { selectedMode ->
                            DropdownMenuItem(
                                text = { Text(selectedMode) },
                                onClick = {
                                    onSetMode(selectedMode)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            //Temperature
            Text(
                text = "Temperature:",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$temperature°C",
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { if (temperature > 0) onSetTemperature(temperature - 1) },
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
                    value = temperature.toFloat(),
                    onValueChange = { newValue ->
                        onSetTemperature(newValue.toInt())
                    },
                    valueRange = 18f..38f,
                    steps = 19,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { if (temperature < 100) onSetTemperature(temperature + 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            //Vertical Swings
            Text(
                text = "Vertical Swing:",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = verticalSwingPositions[verticalSwing],
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Slider(
                    value = verticalSwing.toFloat(),
                    onValueChange = { newValue ->
                        //index is 0 for auto
                        //set vertical swing gets index value therefore in set should modify accordingly
                        onSetVerticalSwing(newValue.toInt())
                    },
                    valueRange = 0f..5f,
                    steps = 5, // Ensures the slider snaps to integer values
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                verticalSwingPositions.forEach { label ->
                    Text(text = label)
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            //Horizontal Swings
            Text(
                text = "Horizontal Swing:",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = horizontalSwingPositions[horizontalSwing],
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Slider(
                    value = horizontalSwing.toFloat(),
                    onValueChange = { newValue ->
                        //index is 0 is for -90°
                        //set horizontal swing gets index value therefore in set should modify accordingly
                        onSetHorizontalSwing(newValue.toInt())
                    },
                    valueRange = 0f..4f,
                    steps = 4,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                horizontalSwingPositions.forEach { label ->
                    Text(text = label)
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            //Fan Speed
            Text(
                text = "Fan Speed:",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$fanSpeed%",
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Slider(
                    value = fanSpeed.toFloat(),
                    onValueChange = { newValue ->
                        // -25 is auto and the rest are in percentage
                        onSetFanSpeed(newValue.toInt())
                    },
                    valueRange = 0f..100f,
                    steps = 3,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                fanSpeedPositions.forEach { label ->
                    Text(text = label)
                }
            }

        }
    }
}

@Composable
fun AirConditionerScreenPreview() {
    var isOn by remember { mutableStateOf(true) }
    var temperature by remember { mutableIntStateOf(24) }
    var mode by remember { mutableStateOf("Cooling") }
    var verticalSwing by remember { mutableIntStateOf(2) }
    var horizontalSwing by remember { mutableIntStateOf(0) }
    var fanSpeed by remember { mutableIntStateOf(25) }

    Surface {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AirConditionerScreen(
                deviceName = "Living Room Air Conditioner",
                isOn = isOn,
                onToggle = { isOn = it },
                temperature = temperature,
                onSetTemperature = { temperature = it },
                mode = mode,
                onSetMode = { mode = it },
                verticalSwing = verticalSwing,
                onSetVerticalSwing = {verticalSwing = it },
                horizontalSwing = horizontalSwing,
                onSetHorizontalSwing = {horizontalSwing = it },
                fanSpeed = fanSpeed,
                onSetFanSpeed = { fanSpeed = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ACPreview() {
    AirConditionerScreenPreview()
}