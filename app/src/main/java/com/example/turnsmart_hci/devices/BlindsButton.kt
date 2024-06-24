package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.Blind
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.ui.devices.BlindViewModel
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.tabletVersion.TabletDeviceButton
import com.example.turnsmart_hci.ui.theme.TurnSmartTheme
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_red

@Composable
fun BlindsButton(
    blind: Blind,
    blindViewModel: BlindViewModel,
    notificationViewModel: NotificationViewModel,
    layoutType: NavigationSuiteType
) {
    var showPopup by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if(layoutType == NavigationSuiteType.NavigationBar){
        DeviceButton(
            label = blind.name,
            onClick = {showPopup = true},
            backgroundColor = pale_red,
            icon = R.drawable.blinds,
            power = { on ->
                if (on) {
                    blindViewModel.open(blind)
                    notificationViewModel.sendNotification(context, "Blinds opened",blind.name)
                } else {
                    blindViewModel.close(blind)
                    notificationViewModel.sendNotification(context, "Blinds closed",blind.name)
                }
            },
            device = blind,
            onToggleFavorite = { deviceId ->
                blindViewModel.toggleFavorite(deviceId) // Llama al método toggleFavorite
            }
        )
    }else{
        TabletDeviceButton(
            label = blind.name,
            onClick = {showPopup = true},
            backgroundColor = pale_red,
            icon = R.drawable.blinds,
            power = { on ->
                if (on) {
                    blindViewModel.open(blind)
                    notificationViewModel.sendNotification(context, "Blinds opened",blind.name)
                } else {
                    blindViewModel.close(blind)
                    notificationViewModel.sendNotification(context, "Blinds closed",blind.name)
                }
            },
            status = blind.status.name
        )
    }

    if (showPopup) {
        Popup(onDismissRequest = { showPopup = false }){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ){
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(TurnSmartTheme.colors.background, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ){
                    BlindsScreen(
                        deviceName = blind.name,
                        isOpen = blind.status == Status.OPENED,
                        onToggle = { isOpen ->
                            if (isOpen) {
                                blindViewModel.open(blind)
                                notificationViewModel.sendNotification(context, "Blinds opened",blind.name)
                            } else {
                                blindViewModel.close(blind)
                                notificationViewModel.sendNotification(context, "Blinds closed",blind.name)
                            }
                        },
                        blindPosition = blind.level,
                        onPositionChange = { level ->
                            blindViewModel.setLevel(blind, level)
                            notificationViewModel.sendNotification(context, "Blind position changed to $level%",blind.name)
                        },
                        onBackClick = { showPopup = false }
                    )
                }
            }
        }

    }
}

@Composable
fun BlindsScreen(
    deviceName: String,
    isOpen: Boolean,
    onToggle: (Boolean) -> Unit,
    blindPosition: Int,
    onPositionChange: (Int) -> Unit,
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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
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
                painter = painterResource(id = R.drawable.blinds),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp).padding(10.dp))
            Text(
                text = deviceName,
                color = textColor,
                fontSize = 22.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Bold,
                onTextLayout = {}
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                Text(
                    text = if(isOpen) stringResource(id = R.string.open_blind) else stringResource(id = R.string.close),
                    color = textColor,
                    fontSize = 16.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Medium,
                    onTextLayout = {}
                )
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = isOpen,
                    onCheckedChange = { isChecked ->
                        onToggle(isChecked)
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp).padding(10.dp))
            Text(
                text = stringResource(id = R.string.blind_position) ,
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start).padding(10.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$blindPosition%",
                color = textColor,
                fontSize = 25.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Bold,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                IconButton(
                    onClick = { if (blindPosition > 0) onPositionChange(blindPosition - 1) },
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
                    value = blindPosition.toFloat(),
                    onValueChange = { newValue ->
                        onPositionChange(newValue.toInt())
                    },
                    valueRange = 0f..100f,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { if (blindPosition < 100) onPositionChange(blindPosition + 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = null,
                        tint = textColor
                    )
                }
            }
        }
    }
}

//@Composable
//fun BlindsControlScreen() {
//    var isOpen by remember { mutableStateOf(false) }
//    var blindPosition by remember { mutableStateOf(50) }
//
//    BlindsScreen(
//        deviceName = "Messi's Room Blind",
//        isOpen = isOpen,
//        onToggle = { isOpen = it },
//        blindPosition = blindPosition,
//        onPositionChange = { blindPosition = it }
//    )
//}
//
//@Preview
//@Composable
//fun BlindsButtonPreview() {
//    BlindsButton()
//    BlindsControlScreen()
//}