package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.AC
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.ui.devices.ACViewModel
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.tabletVersion.TabletDeviceButton
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_blue

@Composable
fun ACButton(
    ac: AC,
    acViewModel: ACViewModel,
    notificationViewModel: NotificationViewModel,
    layoutType: NavigationSuiteType
) {
    var showPopup by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (layoutType == NavigationSuiteType.NavigationBar){
        DeviceButton(
            label = ac.name,
            onClick = { showPopup = true },
            backgroundColor = pale_blue,
            icon = R.drawable.ac,
            power = { on ->
                if (on) {
                    acViewModel.turnOn(ac)
                    notificationViewModel.sendNotification(context, R.string.ac_turned_on,ac.name)
                } else {
                    acViewModel.turnOff(ac)
                    notificationViewModel.sendNotification(context, R.string.ac_turned_off,ac.name)
                }
            },
            device = ac,
            onToggleFavorite = { deviceId ->
                acViewModel.toggleFavorite(deviceId) // Llama al método toggleFavorite
            }
        )
    }else{
        TabletDeviceButton(
            label = ac.name,
            onClick = { showPopup = true },
            backgroundColor = pale_blue,
            icon = R.drawable.ac,
            power = { on ->
                if (on) {
                    acViewModel.turnOn(ac)
                    notificationViewModel.sendNotification(context, R.string.ac_turned_on,ac.name)
                } else {
                    acViewModel.turnOff(ac)
                    notificationViewModel.sendNotification(context, R.string.ac_turned_off,ac.name)
                }
            },
            status = ac.status.name,
            device = ac,
            onToggleFavorite = { deviceId ->
                acViewModel.toggleFavorite(deviceId) // Llama al método toggleFavorite
            }
        )
    }

    if (showPopup) {
        Popup(onDismissRequest = { showPopup = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(
                            TurnSmartTheme.colors.background,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                ) {
                    AirConditionerScreen(
                        deviceName = ac.name,
                        isOn = ac.status == Status.ON,
                        onToggle = { isOn ->
                            if (isOn) {
                                acViewModel.turnOn(ac)
                                notificationViewModel.sendNotification(context, R.string.ac_turned_on,ac.name)
                            } else {
                                acViewModel.turnOff(ac)
                                notificationViewModel.sendNotification(context, R.string.ac_turned_off,ac.name)
                            }
                        },
                        temperature = ac.temperature,
                        onSetTemperature = { temp ->
                            acViewModel.setTemperature(ac, temp)
                            notificationViewModel.sendNotification(context, R.string.temperature_changed,ac.name,temp)
                        },
                        mode = ac.mode,
                        onSetMode = { mod ->
                            acViewModel.setMode(ac, mod)
                            notificationViewModel.sendNotification(context, R.string.mode_changed,ac.name, mod)
                        },
                        verticalSwing = ac.verticalSwing,
                        onSetVerticalSwing = { vSwing ->
                            acViewModel.setVerticalSwing(ac, vSwing)
                            notificationViewModel.sendNotification(context, R.string.vertical_swing_changed,ac.name,vSwing)
                        },
                        horizontalSwing = ac.horizontalSwing,
                        onSetHorizontalSwing = { hSwing ->
                            acViewModel.setHorizontalSwing(ac, hSwing)
                            notificationViewModel.sendNotification(context, R.string.horizontal_swing_changed,ac.name,hSwing)
                        },
                        fanSpeed = ac.fanSpeed,
                        onSetFanSpeed = { speed ->
                            acViewModel.setFanSpeed(ac, speed)
                            notificationViewModel.sendNotification(context, R.string.fan_speed_changed,ac.name, speed)
                        },
                        onBackClick = { showPopup = false }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
    textColor: Color = TurnSmartTheme.colors.onPrimary,
    backgroundColor: Color = TurnSmartTheme.colors.background,
    onBackClick: () -> Unit
) {
    val modes = listOf(stringResource(id = R.string.fan), stringResource(id = R.string.cool), stringResource(id = R.string.heat))
    var expanded by remember { mutableStateOf(false) }
    val horizontalSwingPositions = listOf("Auto", "-90°", "-45°", "0°", "45°", "90°")
    val verticalSwingPositions = listOf("Auto", "0°", "22°", "45°", "67°", "90°")
    val fanSpeedPositions = listOf("Auto", "25%", "50%", "75%", "100%")

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = if (isOn) stringResource(id = R.string.turn_on) else stringResource(id = R.string.turn_off),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.mode),
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

            Spacer(modifier = Modifier
                .height(25.dp)
                .padding(10.dp))

            // Temperature
            Text(
                text = stringResource(id = R.string.temperature),
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier
                .height(10.dp)
                .padding(10.dp))
            Text(
                text = "$temperature°C",
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                IconButton(
                    onClick = { if (temperature > 0) onSetTemperature(temperature - 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus),
                        contentDescription = null,
                        tint = textColor
                    )
                }
                Spacer(modifier = Modifier
                    .width(8.dp)
                    .padding(10.dp))
                Slider(
                    value = temperature.toFloat(),
                    onValueChange = { newValue ->
                        onSetTemperature(newValue.toInt())
                    },
                    valueRange = 18f..38f,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier
                    .width(8.dp)
                    .padding(10.dp))
                IconButton(
                    onClick = { if (temperature < 100) onSetTemperature(temperature + 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = null,
                        tint = textColor
                    )
                }
            }

            Spacer(modifier = Modifier
                .height(25.dp)
                .padding(10.dp))

            // Vertical Swing
            Text(
                text = stringResource(id = R.string.v_swing),
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier
                .height(10.dp)
                .padding(10.dp))
            Text(
                text = verticalSwing,
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val verticalSwingIndex = verticalSwingPositions.indexOf(verticalSwing)
                Slider(
                    value = if (verticalSwingIndex >= 0) verticalSwingIndex.toFloat() else 0f,
                    onValueChange = { newValue ->
                        onSetVerticalSwing(verticalSwingPositions[newValue.toInt()])
                    },
                    valueRange = 0f..(verticalSwingPositions.size - 1).toFloat(),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                verticalSwingPositions.forEach { label ->
                    Text(text = label)
                }
            }

            Spacer(modifier = Modifier
                .height(25.dp)
                .padding(10.dp))

            // Horizontal Swing
            Text(
                text = stringResource(id = R.string.h_swing),
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier
                .height(10.dp)
                .padding(10.dp))
            Text(
                text = horizontalSwing,
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val horizontalSwingIndex = horizontalSwingPositions.indexOf(horizontalSwing)
                Slider(
                    value = if (horizontalSwingIndex >= 0) horizontalSwingIndex.toFloat() else 0f,
                    onValueChange = { newValue ->
                        onSetHorizontalSwing(horizontalSwingPositions[newValue.toInt()])
                    },
                    valueRange = 0f..(horizontalSwingPositions.size - 1).toFloat(),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                horizontalSwingPositions.forEach { label ->
                        Text(text = label)

                }
            }
            Spacer(modifier = Modifier
                .height(25.dp)
                .padding(10.dp))

            // Fan Speed
            Text(
                text = stringResource(id = R.string.fan_speed),
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier
                .height(10.dp)
                .padding(10.dp))
            Text(
                text = fanSpeed,
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val fanSpeedIndex = fanSpeedPositions.indexOf(fanSpeed)
                Slider(
                    value = if (fanSpeedIndex >= 0) fanSpeedIndex.toFloat() else 0f,
                    onValueChange = { newValue ->
                        onSetFanSpeed(fanSpeedPositions[newValue.toInt()])
                    },
                    valueRange = 0f..(fanSpeedPositions.size - 1).toFloat(),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                fanSpeedPositions.forEach { label ->
                    Text(text = label)
                }
            }
        }

    }
}