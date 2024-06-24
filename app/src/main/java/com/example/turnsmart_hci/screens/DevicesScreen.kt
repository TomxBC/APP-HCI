package com.example.turnsmart_hci.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turnsmart_hci.data.model.AC
import com.example.turnsmart_hci.data.model.Blind
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.model.Speaker
import com.example.turnsmart_hci.data.ui.devices.ACViewModel
import com.example.turnsmart_hci.data.ui.devices.BlindViewModel
import com.example.turnsmart_hci.data.ui.devices.DevicesViewModel
import com.example.turnsmart_hci.data.ui.devices.LampViewModel
import com.example.turnsmart_hci.data.ui.devices.SpeakerViewModel
import com.example.turnsmart_hci.data.ui.getViewModelFactory
import com.example.turnsmart_hci.devices.ACButton
import com.example.turnsmart_hci.devices.BlindsButton
import com.example.turnsmart_hci.devices.LightButton
import com.example.turnsmart_hci.devices.SpeakerButton
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.R

@Composable
fun DevicesScreen(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
    lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory()),
    acViewModel: ACViewModel = viewModel(factory = getViewModelFactory()),
    blindViewModel: BlindViewModel = viewModel(factory = getViewModelFactory()),
    speakerViewModel: SpeakerViewModel = viewModel(factory = getViewModelFactory()),
    notificationViewModel: NotificationViewModel,
    layoutType: NavigationSuiteType
) {
    val uiState by viewModel.uiState.collectAsState()

    TurnSmartTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(8.dp)
                    .then(if(layoutType == NavigationSuiteType.NavigationBar) Modifier.verticalScroll(rememberScrollState()) else(Modifier) ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val devices = uiState.devices

                if (devices.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_devices),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontFamily = montserratFontFamily,
                        color = TurnSmartTheme.colors.onPrimary
                    )
                } else {
                    Text(
                        text = stringResource(R.string.have_devices),
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(20.dp),
                        color = TurnSmartTheme.colors.onPrimary
                    )

                    if (layoutType == NavigationSuiteType.NavigationRail || layoutType == NavigationSuiteType.NavigationDrawer) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(1.dp)
                        ) {
                            items(devices.size) { index ->
                                when (val device = devices[index]) {
                                    is Lamp -> {
                                        LightButton(
                                            lamp = device,
                                            lampViewModel = lampViewModel,
                                            notificationViewModel = notificationViewModel,
                                            layoutType = layoutType
                                        )
                                    }
                                    is AC -> {
                                        ACButton(
                                            ac = device,
                                            acViewModel = acViewModel,
                                            notificationViewModel = notificationViewModel,
                                            layoutType = layoutType
                                        )
                                    }
                                    is Blind -> {
                                        BlindsButton(
                                            blind = device,
                                            blindViewModel = blindViewModel,
                                            notificationViewModel = notificationViewModel,
                                            layoutType = layoutType
                                        )
                                    }
                                    is Speaker -> {
                                        SpeakerButton(
                                            speaker = device,
                                            speakerViewModel = speakerViewModel,
                                            notificationViewModel = notificationViewModel,
                                            layoutType = layoutType
                                        )
                                    }
                                    else -> {
                                        Text(stringResource(R.string.unkown))
                                    }
                                }
                            }
                        }
                    } else {
                        devices.forEach { device ->
                            when (device) {
                                is Lamp -> {
                                    LightButton(
                                        lamp = device,
                                        lampViewModel = lampViewModel,
                                        notificationViewModel = notificationViewModel,
                                        layoutType = layoutType
                                    )
                                }
                                is AC -> {
                                    ACButton(
                                        ac = device,
                                        acViewModel = acViewModel,
                                        notificationViewModel = notificationViewModel,
                                        layoutType = layoutType
                                    )
                                }
                                is Blind -> {
                                    BlindsButton(
                                        blind = device,
                                        blindViewModel = blindViewModel,
                                        notificationViewModel = notificationViewModel,
                                        layoutType = layoutType
                                    )
                                }
                                is Speaker -> {
                                    SpeakerButton(
                                        speaker = device,
                                        speakerViewModel = speakerViewModel,
                                        notificationViewModel = notificationViewModel,
                                        layoutType = layoutType
                                    )
                                }
                                else -> {
                                    Text(stringResource(R.string.unkown))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
