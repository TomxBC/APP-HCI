package com.example.turnsmart_hci.data.ui.devices

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turnsmart_hci.data.model.AC
import com.example.turnsmart_hci.data.model.Blind
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.model.Speaker
import com.example.turnsmart_hci.data.ui.getViewModelFactory
import com.example.turnsmart_hci.devices.ACButton
import com.example.turnsmart_hci.devices.BlindsButton
import com.example.turnsmart_hci.devices.LightButton
import com.example.turnsmart_hci.devices.SpeakerButton
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.dark_purple
import com.example.turnsmart_hci.ui.theme.montserratFontFamily

@Composable
fun DevicesScreen(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
    lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory()),
    acViewModel: ACViewModel = viewModel(factory = getViewModelFactory()),
    blindViewModel: BlindViewModel = viewModel(factory = getViewModelFactory()),
    speakerViewModel: SpeakerViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsState()
    val uiLampState by lampViewModel.uiState.collectAsState()

    TurnSmartTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val devices = uiState.devices

                if(devices.isEmpty()){
                    Text(
                        text = "You have not devices, $devices",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                } else {
                    Text(
                        text = "Your Devices",
                        fontFamily = montserratFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 30.sp, color = dark_purple,
                        modifier = Modifier.padding(20.dp),

                    )
                    devices.forEach { device ->
                        when (device) {
                            is Lamp -> {
                                LightButton(
                                    lamp = device,
                                    lampViewModel = lampViewModel
                                )
                            }
                            is AC -> {
                                ACButton(
                                    ac = device,
                                    acViewModel = acViewModel
                                )
                            }
                            is Blind -> {
                                BlindsButton(
                                    blind = device,
                                    blindViewModel = blindViewModel
                                )
                            }
                            is Speaker -> {
                                SpeakerButton(
                                    speaker = device,
                                    speakerViewModel = speakerViewModel
                                )

                            } else -> {
                                Text("Unknown device type")
                            }
                        }
                    }
                }
            }
        }
    }
}



//        when (uiLampState.currentDevice?.status) {
//            Status.ON -> {
//                DeviceButton(
//                    label = R.string.turn_off,
//                    enabled = uiLampState.canExecuteAction,
//                    onClick = { lampViewModel.turnOff() },
//                    backgroundColor = ThemeColors.PALE_YELLOW.color,
//                    icon = R.drawable.lights
//                )
//            }
//
//            else -> {
//                DeviceButton(
//                    label = R.string.turn_on,
//                    enabled = uiLampState.canExecuteAction,
//                    onClick = { lampViewModel.turnOn() },
//                    backgroundColor = ThemeColors.PALE_YELLOW.color,
//                    icon = R.drawable.lights
//                )
//            }
//        }


//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Text(
//                text = uiState.error?.message ?: "",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
//                fontSize = 18.sp
//            )
//            val currentDeviceData = uiLampState.currentDevice?.let {
//                "(${it.id}) ${it.name}"
//            } ?: stringResource(R.string.unknown)
//            Text(
//                text = stringResource(R.string.current_device, currentDeviceData),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
//                fontSize = 18.sp
//            )
//            val currentDeviceStatus = uiLampState.currentDevice?.let {
//                when (it.status) {
//                    Status.ON -> stringResource(R.string.status_on)
//                    Status.OFF -> stringResource(R.string.status_off)
//                }
//            } ?: stringResource(R.string.unknown)
//            Text(
//                text = stringResource(
//                    R.string.device_status,
//                    currentDeviceStatus
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
//                fontSize = 18.sp
//            )
//            Text(
//                text = stringResource(R.string.total_devices, uiState.devices.size),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
//                fontSize = 18.sp
//            )
//        }

