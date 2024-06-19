package com.example.turnsmart_hci.tabletVersion

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turnsmart_hci.R
import com.example.turnsmart_hci.ui.theme.montserratFontFamily
import com.example.turnsmart_hci.ui.theme.ThemeColors

@Composable
fun TabletDeviceButton(
    @StringRes label: Int,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = ThemeColors.DARK_TEXT.color,
    icon: Int,
    isOn: Boolean = false,
    onButton: (Boolean) -> Unit = {},
    status: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        modifier = modifier
            .padding(8.dp)
            .size(300.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            IconButton(
                onClick = { /* Handle favorite icon click */ },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top= 16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.favorite),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = {
                    onButton(isOn)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top= 16.dp)
                    .border(2.dp, Color.Black, CircleShape)
                    .background(Color.White, CircleShape),
            ) {
                Icon(
                    painter = painterResource(R.drawable.power),
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
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .size(60.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = label),
                    color = textColor,
                    fontSize = 25.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = status,
                color = textColor,
                fontSize = 20.sp,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp)
            )
        }
    }
}


@Preview
@Composable
fun TabletDeviceButtonPreview() {
    // Sample preview function
    TabletDeviceButton(
            label = R.string.lights, onClick = {},
            icon = R.drawable.lights, backgroundColor = ThemeColors.PALE_YELLOW.color,
            status = "Off"
    )
}
