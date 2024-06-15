package com.example.turnsmart_hci.devices

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.darkText
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.pale_blue

@Composable
fun RoutineButton(
    label: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = darkText,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        modifier = modifier
            .padding(8.dp)
            .size(width = 300.dp, height = 50.dp),
    ) {
        IconButton(
            onClick = { }, // ESTE EJECUTARIA
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.favorite), // CAMBIAR EL LOGO A PLAY
                contentDescription = null,
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = label,
            color = textColor,
            fontSize = 18.sp,
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(60.dp))
        IconButton(
            onClick = { }
        ){
            Icon(
                painter = painterResource(R.drawable.favorite),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black)
        }

    }
}

@Preview
@Composable
fun RoutineButtonPreview() {
    RoutineButton(label = "RoutineName", onClick = {}, backgroundColor = pale_blue)
}