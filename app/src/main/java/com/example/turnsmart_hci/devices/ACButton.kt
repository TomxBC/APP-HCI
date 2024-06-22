package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.AC
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.ui.devices.ACViewModel
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_blue

@Composable
fun ACButton(ac: AC, acViewModel: ACViewModel) {
    var showPopup by remember { mutableStateOf(false) }

    DeviceButton(
        label = ac.name,
        onClick = { showPopup = true },
        backgroundColor = pale_blue,
        icon = R.drawable.ac
    )

    if (showPopup) {
        Popup(onDismissRequest = { showPopup = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { showPopup = false }
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(pale_blue, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    AirConditionerScreen(
                        deviceName = ac.name,
                        isOn = ac.status == Status.ON,
                        onToggle = { isOn ->
                            if (isOn) {
                                acViewModel.turnOn(ac)
                            } else {
                                acViewModel.turnOff(ac)
                            }
                        },
                        temperature = ac.temperature,
                        onSetTemperature = { temp ->
                            acViewModel.setTemperature(ac, temp)
                        },
                        mode = ac.mode,
                        onSetMode = { mod ->
                            acViewModel.setMode(ac, mod)
                        },
                        verticalSwing = ac.verticalSwing,
                        onSetVerticalSwing = { vSwing ->
                            acViewModel.setVerticalSwing(ac, vSwing)
                        },
                        horizontalSwing = ac.horizontalSwing,
                        onSetHorizontalSwing = { hSwing ->
                            acViewModel.setHorizontalSwing(ac, hSwing)
                        },
                        fanSpeed = ac.fanSpeed,
                        onSetFanSpeed = { speed ->
                            acViewModel.setFanSpeed(ac, speed)
                        },
                        backgroundColor = pale_blue,
                        onBackClick = { showPopup = false } // Pass the back click handler
                    )
                }
            }
        }
    }
}

@Composable
fun AirConditionerScreen(
    deviceName: String,
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    temperature: Int,
    onSetTemperature: (Int) -> Unit,
    mode: String,
    onSetMode: (String) -> Unit,
    verticalSwing: String,
    onSetVerticalSwing: (String) -> Unit,
    horizontalSwing: String,
    onSetHorizontalSwing: (String) -> Unit,
    fanSpeed: String,
    onSetFanSpeed: (String) -> Unit,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White, // Add a parameter for background color
    onBackClick: () -> Unit // Add a parameter for back click handler
) {
    val modes = listOf("Fan", "Cooling", "Heating")
    var expanded by remember { mutableStateOf(false) }
    val horizontalSwingPositions = listOf("Auto", "-90°", "-45°", "0°", "45°", "90°")
    val verticalSwingPositions = listOf("Auto", "0°", "22°", "45°", "67°", "90°")
    val fanSpeedPositions = listOf("Auto", "25%", "50%", "75%", "100%")

    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(8.dp)) // Use the background color parameter
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            // Back Arrow
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
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(30.dp))

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

            // Modes
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
                            .clickable { expanded = !expanded },
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

            // Temperature
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

            // Vertical Swing
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
                text = verticalSwing,
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val verticalSwingIndex = verticalSwingPositions.indexOf(verticalSwing)
                Slider(
                    value = if (verticalSwingIndex >= 0) verticalSwingIndex.toFloat() else 0f,
                    onValueChange = { newValue ->
                        onSetVerticalSwing(verticalSwingPositions[newValue.toInt()])
                    },
                    valueRange = 0f..(verticalSwingPositions.size - 1).toFloat(),
                    steps = verticalSwingPositions.size - 1,
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

            // Horizontal Swing
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
                text = horizontalSwing,
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val horizontalSwingIndex = horizontalSwingPositions.indexOf(horizontalSwing)
                Slider(
                    value = if (horizontalSwingIndex >= 0) horizontalSwingIndex.toFloat() else 0f,
                    onValueChange = { newValue ->
                        onSetHorizontalSwing(horizontalSwingPositions[newValue.toInt()])
                    },
                    valueRange = 0f..(horizontalSwingPositions.size - 1).toFloat(),
                    steps = horizontalSwingPositions.size - 1,
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

            // Fan Speed
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
                text = fanSpeed,
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val fanSpeedIndex = fanSpeedPositions.indexOf(fanSpeed)
                Slider(
                    value = if (fanSpeedIndex >= 0) fanSpeedIndex.toFloat() else 0f,
                    onValueChange = { newValue ->
                        onSetFanSpeed(fanSpeedPositions[newValue.toInt()])
                    },
                    valueRange = 0f..(fanSpeedPositions.size - 1).toFloat(),
                    steps = fanSpeedPositions.size - 1,
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

//@Composable
//fun AirConditionerScreenPreview() {
//    var isOn by remember { mutableStateOf(true) }
//    var temperature by remember { mutableIntStateOf(24) }
//    var mode by remember { mutableStateOf("Cooling") }
//    var verticalSwing by remember { mutableIntStateOf(2) }
//    var horizontalSwing by remember { mutableIntStateOf(0) }
//    var fanSpeed by remember { mutableIntStateOf(25) }
//
//    Surface {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            AirConditionerScreen(
//                deviceName = "Living Room Air Conditioner",
//                isOn = isOn,
//                onToggle = { isOn = it },
//                temperature = temperature,
//                onSetTemperature = { temperature = it },
//                mode = mode,
//                onSetMode = { mode = it },
//                verticalSwing = verticalSwing,
//                onSetVerticalSwing = { },
//                horizontalSwing = horizontalSwing,
//                onSetHorizontalSwing = { },
//                fanSpeed = fanSpeed,
//                onSetFanSpeed = { }
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ACPreview() {
//    AirConditionerScreenPreview()
//}
