package com.example.turnsmart_hci.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.Blind
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.ui.devices.BlindViewModel
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_blue
import com.example.turnsmart_hci.ui.theme.pale_red
import com.example.turnsmart_hci.ui.theme.pale_yellow

@Composable
fun BlindsButton(blind: Blind, blindViewModel: BlindViewModel) {
    val showDialog = remember { mutableStateOf(false) }

    DeviceButton(
        label = blind.name,
        onClick = {showDialog.value = true},
        backgroundColor = pale_red,
        icon = R.drawable.blinds
    )
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = { Text(text = "Blinds Control") },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "Close")
                }
            },
            text = {
                BlindsScreen(
                    deviceName = blind.name,
                    isOpen = blind.status == Status.OPENED,
                    onToggle = { isOpen ->
                        if (isOpen) {
                            blindViewModel.open(blind)
                        } else {
                            blindViewModel.close(blind)
                        }
                    },
                    blindPosition = blind.level,
                    onPositionChange = { level ->
                        blindViewModel.setLevel(blind, level)
                    },
                    textColor = Color.Black,
                )
            }
        )
    }
}

@Composable
fun BlindsScreen(
    deviceName: String,
    isOpen: Boolean,
    onToggle: (Boolean) -> Unit,
    blindPosition: Int,
    onPositionChange: (Int) -> Unit,
    textColor: Color = Color.Black,
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
                painter = painterResource(id = R.drawable.blinds),
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
                onTextLayout = {}
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if(isOpen) "OPEN" else "CLOSE",
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Blind position: $blindPosition",
                color = textColor,
                fontSize = 16.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                onTextLayout = {}
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { if (blindPosition > 0) onPositionChange(blindPosition - 1) },
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
                    value = blindPosition.toFloat(),
                    onValueChange = { newValue ->
                        onPositionChange(newValue.toInt())
                    },
                    valueRange = 0f..100f,
                    steps = 99, // Ensures the slider snaps to integer values
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
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
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