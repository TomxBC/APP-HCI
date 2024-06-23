package com.example.turnsmart_hci.tabletVersion

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.Routine
import com.example.turnsmart_hci.data.ui.routines.RoutineViewModel
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.ThemeColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.turnsmart_hci.ui.theme.pale_blue

@Composable
fun TabletRoutineButton(
    routine: Routine,
    notificationViewModel: NotificationViewModel,
    routineViewModel: RoutineViewModel,
    enabled: Boolean = true,
    textColor: Color = ThemeColors.DARK_TEXT.color,
    isOn: Boolean = false,
    onButton: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var isPlaying by remember { mutableStateOf(isOn) }
    val scope = rememberCoroutineScope()

    Button(
        onClick = { /* Handle button click if needed */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = pale_blue,
        ),
        modifier = modifier
            .padding(8.dp)
            .size(250.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(
                    onClick = {
                        isPlaying = !isPlaying
                        if (isPlaying) {
                            scope.launch {
                                notificationViewModel.sendNotification(context,R.string.routine_executing,routine.name)
                                delay(5000)
                                isPlaying = false }
                            routineViewModel.executeRoutine(routine)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top= 16.dp)
                        .border(2.dp, Color.Black, CircleShape)
                        .background(Color.White, CircleShape),
                ) {
                    Icon(
                        painter = painterResource(if (isPlaying) R.drawable.pause else R.drawable.play_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color.Black
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = routine.name.toString(),
                        color = textColor,
                        fontSize = 25.sp,
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun TabletRoutineButtonPreview() {
//    TabletRoutineButton(
//        label = "Routine1",
//        onPlayClick = {},
//        onFavoriteClick = {},
//    )
//}
