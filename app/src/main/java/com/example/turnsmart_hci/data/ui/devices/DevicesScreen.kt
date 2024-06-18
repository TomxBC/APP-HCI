package com.example.turnsmart_hci.data.ui.devices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.devices.DeviceButton
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.ui.getViewModelFactory
import com.example.turnsmart_hci.ui.theme.ThemeColors


@Composable
fun DevicesScreen(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
    lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()
    val uiLampState by lampViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        when (uiLampState.currentDevice?.status) {
            Status.ON -> {
                DeviceButton(
                    label = R.string.turn_off,
                    enabled = uiLampState.canExecuteAction,
                    onClick = { lampViewModel.turnOff() },
                    backgroundColor = ThemeColors.PALE_YELLOW.color,
                    icon = R.drawable.lights
                )
            }

            else -> {
                DeviceButton(
                    label = R.string.turn_on,
                    enabled = uiLampState.canExecuteAction,
                    onClick = { lampViewModel.turnOn() },
                    backgroundColor = ThemeColors.PALE_YELLOW.color,
                    icon = R.drawable.lights
                )
            }
        }

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
    }
}

@Preview
@Composable
fun DevicesScreenPreview() {
    DevicesScreen()
}