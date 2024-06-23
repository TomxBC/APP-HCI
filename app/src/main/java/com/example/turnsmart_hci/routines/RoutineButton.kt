package com.example.turnsmart_hci.routines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.data.model.Routine
import com.example.turnsmart_hci.data.ui.routines.RoutineViewModel
import com.example.turnsmart_hci.notifications.NotificationViewModel
import com.example.turnsmart_hci.ui.theme.darkText
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_blue

@Composable
fun RoutineButton(
    routine: Routine,
    routineViewModel: RoutineViewModel,
    notificationViewModel: NotificationViewModel
) {
    val context = LocalContext.current

    Button(
        onClick = { /* Handle button click if needed */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = pale_blue
        ),
        modifier = Modifier
            .padding(8.dp)
            .size(width = 300.dp, height = 70.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = routine.name.toString(),
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium
            )
            IconButton(
                onClick = {
                    //CREO QUE FALTA API EXECUTE
                    notificationViewModel.sendNotification(context,"Routine is being executed",routine.name)
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.play_circle),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}